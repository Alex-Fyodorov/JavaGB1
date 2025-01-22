package nets.chat.server;

import java.sql.*;

public class DataBaseAuthService implements AuthService{
    private Connection connection;
    private Statement statement;

    @Override
    public void start() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:ChatDB.db");
            statement = connection.createStatement();
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    @Override
    public void stop() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        try (PreparedStatement ps = connection.prepareStatement("select nick from accounts where login = ? and password = ?;")) {
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            return rs.getString("nick");
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeNick(String name, String nick) {
        try (ResultSet rs = statement.executeQuery("select * from accounts;")) {
            while (rs.next()) {
                if (rs.getString("nick").equals(nick)) {
                    return false;
                }
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        try (PreparedStatement ps = connection.prepareStatement("update accounts set nick = ? where nick = ?;")) {
            ps.setString(1, nick);
            ps.setString(2, name);
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("select * from accounts where nick = ?;");
            ps2.setString(1, nick);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.getString("nick") != null) {
                return true;
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return false;
    }
}
