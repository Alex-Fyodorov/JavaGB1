package javathree.hw4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateApp {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            insert(sessionFactory);
            printStudents(findAllStudents(sessionFactory));
            printStudents(findStudentsByGroupName(sessionFactory, "Chemistry"));
            findGroupInfo(sessionFactory, "Chemistry");
        }
    }

    public static void insert(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new Group("Chemistry"));
            session.persist(new Group("Physics"));
            session.persist(new Group("Math"));
            transaction.commit();
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new Student("Marilyn", "Monroe"
                    , findByName(sessionFactory, "Chemistry")));
            session.persist(new Student("Ginger", "Rogers"
                    , findByName(sessionFactory, "Physics")));
            session.persist(new Student("Gregory", "Peck"
                    , findByName(sessionFactory, "Math")));
            session.persist(new Student("Marlon", "Brando"
                    , findByName(sessionFactory, "Chemistry")));
            session.persist(new Student("James", "Cagney"
                    , findByName(sessionFactory, "Chemistry")));
            session.persist(new Student("James", "Stewart"
                    , findByName(sessionFactory, "Math")));
            transaction.commit();
        }
    }

    public static Group findByName(SessionFactory sessionFactory, String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select g from Group g where name = :name", Group.class)
                    .setParameter("name", name).getSingleResult();
        }
    }

    public static List<Student> findAllStudents(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select s from Student s", Student.class)
                    .getResultList();
        }
    }

    public static List<Student> findStudentsByGroupName(SessionFactory sessionFactory, String groupName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select s from Student s where s.group.name = :name", Student.class)
                    .setParameter("name", groupName).getResultList();
        }
    }

    public static void findGroupInfo(SessionFactory sessionFactory, String groupName) {
        try (Session session = sessionFactory.openSession()) {
            Group group = session.createQuery(
                            "select g from Group g where g.name = :name", Group.class)
                    .setParameter("name", groupName).getSingleResult();
            System.out.println(group);
        }
    }

    public static void printStudents(List<Student> students) {
        System.out.println("==========================");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("==========================");
    }
}
