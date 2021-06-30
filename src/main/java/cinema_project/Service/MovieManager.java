package cinema_project.Service;

import cinema_project.EntityClass.MovieTypeEntity;
import cinema_project.EntityClass.MoviesEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class MovieManager {

    public static void manualInsertMovie() {
        MovieManager movieManager = new MovieManager();

        insertMovie("Fast & Furious 9", 120, "10.00", "Action");
        insertMovie("Bigfoot Family", 110, "11.00", "Family");
        insertMovie("The Hitman's Wife's Bodyguard", 98, "12.00", "Comedy");
        insertMovie("Peter Rabbit: The Runaway", 93, "14.00", "Family");
        insertMovie("Spiral: From the Book of Saw", 102, "16.00", "Action");

    }

    public static void manualInsertMovieType() {
        insertMovieType("Action");
        insertMovieType("Family");
        insertMovieType("Comedy");
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

    public static void insertMovieType(String movie_type) {

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

    public static MoviesEntity findMovie(int id) {

        Session session = DataBaseService.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + MoviesEntity.class.getName() + " where movie_id=" + id + "");
        MoviesEntity result = (MoviesEntity) query.getSingleResult();
        transaction.commit();
        session.close();
        return result;

    }
}
