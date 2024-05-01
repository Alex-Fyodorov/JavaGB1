package javathree.hw3;

import java.sql.*;

public class DBApp {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:gbdb")) {
            acceptConnection(connection);
        } catch (SQLException e) {
            System.err.println("Не удалось подключьться к БД: " + e.getMessage());
        }
    }

    private static void acceptConnection(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table groups " +
                    "(id int not null auto_increment, group_name varchar(128), primary key (id))");
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("create table students " +
                    "(id bigint not null auto_increment, first_name varchar(256), second_name varchar(256), " +
                    "group_id int, primary key (id), foreign key (group_id) references groups (id))");
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("insert into groups (group_name) values " +
                    "('Chemistry'), ('Physics'), ('Math')");
            System.out.printf("Insert in groups %d notes.\n", count);
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("insert into students (first_name, second_name, group_id) values " +
                    "('Marilyn', 'Monroe', 1), ('Ginger', 'Rogers', 2), ('Gregory', 'Peck', 3), " +
                    "('Marlon', 'Brando', 1), ('James', 'Cagney', 1), ('James', 'Stewart', 3)");
            System.out.printf("Insert in students %d notes.\n", count);
        }

        System.out.println("===================================");

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select students.id, first_name, second_name, group_name " +
                    "from students join groups on group_id = groups.id");
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String groupName = resultSet.getString("group_name");
                System.out.printf("%d, %s, %s, %s\n", id, firstName, secondName, groupName);
            }
        }

        System.out.println("===================================");

        printResultSet(findStudentsByGroupName(connection, "Chemistry"));
    }

    public static ResultSet findStudentsByGroupName(Connection connection, String groupName) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("select students.id, first_name, second_name, group_name " +
                "from students join groups on group_id = groups.id where group_name = ?");
        preparedStatement.setString(1, groupName);
        return preparedStatement.executeQuery();
    }

    public static void printResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            long id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String secondName = resultSet.getString("second_name");
            String groupName = resultSet.getString("group_name");
            System.out.printf("%d, %s, %s, %s\n", id, firstName, secondName, groupName);
        }
    }
}
