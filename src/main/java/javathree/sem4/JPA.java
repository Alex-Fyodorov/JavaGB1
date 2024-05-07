package javathree.sem4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class JPA {

    public static void main(String[] args) throws SQLException {

        run();

//        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "root", "root")) {
//            prepareTable(connection);
//
//            test(connection);
//        }
    }

    public static void run() throws SQLException {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(new User("Pamella", true));
                session.persist(new User("Marilyn", true));
                transaction.commit();
            }

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                User user1 = session.find(User.class, 1L);
                session.persist(new Animal("Pussy", user1));
                User user2 = session.find(User.class, 2L);
                session.persist(new Animal("Kitty", user2));
                transaction.commit();
            }

            try (Session session = sessionFactory.openSession()) {
                User user = session.find(User.class, 1L);
                System.out.println(user);
            }

            try (Session session = sessionFactory.openSession()) {
                User user = session.find(User.class, 2L);
                System.out.println(user);
            }

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                User user = session.createQuery("select u from User u where u.login = :login",
                        User.class).setParameter("login", "Pamella").getSingleResult();
                user.setActive(false);
                session.merge(user);
                transaction.commit();
            }

            try (Session session = sessionFactory.openSession()) {
                User user = session.find(User.class, 1L);
                System.out.println(user);
            }

            try (Session session = sessionFactory.openSession()) {
                Animal animal = session.find(Animal.class, 1L);
                System.out.println(animal);
            }
        }
    }

    private static void prepareTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table users " +
                    "(id bigint not null auto_increment, login varchar(256), active boolean, primary key (id))");
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("insert into users (login, active) values " +
                    "('Marilyn', true)");
            System.out.printf("Insert in groups %d notes.\n", count);
        }
    }

    private static void test(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, login, active from users");
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                boolean active = resultSet.getBoolean("active");
                System.out.printf("%d, %s, %s\n", id, login, active);
            }
            resultSet.close();
        }
    }
}
