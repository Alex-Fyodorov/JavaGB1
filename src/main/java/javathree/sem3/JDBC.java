package javathree.sem3;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:gbdb", "root", "root")) {
            acceptConnection(connection);
        } catch (SQLException e) {
            System.err.println("Не удалось подключьться к БД: " + e.getMessage());
        }
    }

    private static void acceptConnection(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            boolean flag = statement.execute("create table persons " +
                    "(id bigint primary key, name varchar(256), age smallint);");
            System.out.println(flag);
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("insert into persons (id, name, age) values " +
                    "(1, 'Tomas', 24), (2, 'James', 36), (3, 'Pamella', 31);");
            System.out.println("Количество изменённых строк: " + count);
        }

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, name, age from persons;");
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.printf("%d, %s, %d\n", id, name, age);
            }
        }
    }

    public static void removePersonById(Connection connection, String id) throws SQLException{
//        try (Statement statement = connection.createStatement()) {
//            int deletedRowsCount = statement.executeUpdate("delete from persons where id = " + id);
//            System.out.println("Количество удалённых строк: " + deletedRowsCount);
//        }

        PreparedStatement preparedStatement = connection.prepareStatement("delete from persons where id = ?1");
        preparedStatement.setLong(1, Long.parseLong(id));
        int deletedRowsCount = preparedStatement.executeUpdate();
        System.out.println("Количество удалённых строк: " + deletedRowsCount);
    }
}
