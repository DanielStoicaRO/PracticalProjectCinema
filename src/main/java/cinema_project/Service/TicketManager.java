package cinema_project.Service;

import cinema_project.EntityClass.TicketsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class TicketManager {

    public static List<TicketsEntity> findTicketNumber(int movie_id) {

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
}
