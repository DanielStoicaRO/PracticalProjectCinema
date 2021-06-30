package cinema_project.Service;

import cinema_project.EntityClass.MovieTypeEntity;
import cinema_project.EntityClass.MoviesEntity;
import cinema_project.EntityClass.TicketsEntity;
import cinema_project.EntityClass.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Query;
import java.util.List;
import java.util.Properties;

public class DataBaseService {

    public static SessionFactory getSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/cinema?serverTimezone=UTC");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "meninblack525");
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "update");

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(UserEntity.class);
            configuration.addAnnotatedClass(TicketsEntity.class);
            configuration.addAnnotatedClass(MoviesEntity.class);
            configuration.addAnnotatedClass(MovieTypeEntity.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Session openSession() {
        Session session = getSessionFactory().openSession();
        return session;
    }

    public static List<UserEntity> getUser(String user_name, String password) {
        Session session = DataBaseService.openSession();

        Query query = session.createQuery("FROM UserEntity WHERE user_name = :one AND user_password = :two")
                .setParameter("one", user_name).setParameter("two", password);

        List<UserEntity> users = query.getResultList();

        System.out.println(users);
        session.close();

        return users;
    }
}
