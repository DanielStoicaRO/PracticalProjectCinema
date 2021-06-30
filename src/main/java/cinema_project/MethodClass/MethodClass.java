package cinema_project.MethodClass;

import cinema_project.EntityClass.MovieTypeEntity;
import cinema_project.EntityClass.MoviesEntity;
import cinema_project.EntityClass.TicketsEntity;
import cinema_project.EntityClass.UserEntity;
import cinema_project.Service.DataBaseService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class MethodClass {

    public static void manualInsertMovie() {
        new MethodClass().insertMovie("Fast & Furious 9", 120, "10.00", "Action");
        new MethodClass().insertMovie("Bigfoot Family", 110, "11.00", "Family");
        new MethodClass().insertMovie("The Hitman's Wife's Bodyguard", 98, "12.00", "Comedy");
        new MethodClass().insertMovie("Peter Rabbit: The Runaway", 93, "14.00", "Family");
        new MethodClass().insertMovie("Spiral: From the Book of Saw", 102, "16.00", "Action");

    }

    public static void manualInsertMovieType() {
        new MethodClass().insertMovieType("Action");
        new MethodClass().insertMovieType("Family");
        new MethodClass().insertMovieType("Comedy");
    }

    public static void insertMovie(String movie_title, Integer movie_duration, String movie_schedule, String movieType) {

        try {
            Session session = DataBaseService.getSessionFactory().openSession();

            Query query = session.createQuery("from " + MovieTypeEntity.class.getName() + " where " + "movie_type" + "='" + movieType + "'");
            List<MovieTypeEntity> results = query.getResultList();

            Transaction transaction = session.beginTransaction();

            MoviesEntity movies = new MoviesEntity();
            movies.setMovie_title(movie_title);
            movies.setMovie_duration(movie_duration);
            movies.setMovie_schedule(movie_schedule);

            if (results != null && results.size() != 0) {
                movies.setMovie_type(results.get(0));
            } else {
                MovieTypeEntity moviesType = new MovieTypeEntity();
                moviesType.setMovie_type(movieType);
                session.save(moviesType);
                movies.setMovie_type(moviesType);
            }

            session.save(movies);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertMovieType(String movie_type) {

        try {
            Session session = DataBaseService.getSessionFactory().openSession();

            MovieTypeEntity moviesType = new MovieTypeEntity();
            moviesType.setMovie_type(movie_type);

            Transaction transaction = session.beginTransaction();
            session.save(moviesType);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void registerNewUser(String user_name, String password, String phoneNumber) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = DataBaseService.openSession();
            transaction = session.beginTransaction();
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_name(user_name);
            userEntity.setUser_password(password);
            userEntity.setUser_phone(phoneNumber);
            session.save(userEntity);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static UserEntity login(String user_name, String password) throws Exception {

        List<UserEntity> users = DataBaseService.getUser(user_name, password);

        if (users.size() == 0) {
            throw new Exception("Invalid login details!");
        } else {
            return users.get(0);
        }
    }

    public static void showMovie() {
        {
            try {
                Session session = DataBaseService.getSessionFactory().openSession();
                Query query = session.createQuery("FROM MoviesEntity");
                List<MoviesEntity> moviesEntityList = query.getResultList();
                moviesEntityList.stream().forEach(System.out::println);
                session.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public MoviesEntity findMovie(int id) {

        Session session = DataBaseService.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + MoviesEntity.class.getName() + " where movie_id=" + id + "");
        MoviesEntity result = (MoviesEntity) query.getSingleResult();
        transaction.commit();
        session.close();
        return result;

    }

    public List<TicketsEntity> findTicketNumber(int movie_id) {

        Session session = DataBaseService.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + TicketsEntity.class.getName() + " where movie_id=" + movie_id + "");
        List<TicketsEntity> resultList = (List<TicketsEntity>) query.getResultList();
        transaction.commit();
        session.close();
        return resultList; // cate tichete mai sunt disponibile /film

    }

    public static void insertTickets(int selectMovieNumber) {
        TicketsEntity ticketsEntity = new TicketsEntity();
        ticketsEntity.setMovie_id(selectMovieNumber);
        ticketsEntity.setTicket_price("20");

        Session session = DataBaseService.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticketsEntity);
        transaction.commit();
        session.close();
    }

    public static void deleteMovieFromList(int movie_number) {
        try {
            Session session = DataBaseService.getSessionFactory().openSession();
            MoviesEntity moviesEntity = session.find(MoviesEntity.class, movie_number);
            Transaction transaction = session.beginTransaction();
            session.delete(moviesEntity);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
