package cinema_project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

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
}
