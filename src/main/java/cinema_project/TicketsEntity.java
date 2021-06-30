package cinema_project;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_id;

    private Integer movie_id;
    private Integer user_id;
    private String ticket_type;
    private String ticket_price;

    //@ManyToOne cu UserEntity
    @ManyToOne
    @JoinColumn(name = "ticket_user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    //@ManyToOne cu movies
    @ManyToOne
    @JoinColumn(name = "tickets_movie_id", referencedColumnName = "movie_id")
    private MoviesEntity moviesEntity;


    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "TicketsEntity { " +
                "movie_id = " + movie_id +
                ", ticket_type = ' " + ticket_type + '\'' +
                ", ticket_price = ' " + ticket_price + '\'' +
                '}';
    }
}
