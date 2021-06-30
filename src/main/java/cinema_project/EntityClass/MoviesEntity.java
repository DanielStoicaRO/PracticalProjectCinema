package cinema_project.EntityClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MoviesEntity")
public class MoviesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movie_id;

    private String movie_title;
    private Integer movie_duration;
    private String movie_schedule;

    //@OneToMany cu tickets
    @OneToMany(mappedBy = "moviesEntity")
    private List<TicketsEntity> ticketsEntityList;

    @ManyToOne
    @JoinColumn(name = "movies_type_id", referencedColumnName = "movie_type_id")
    private MovieTypeEntity movieTypeEntity;

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public void setMovie_type(MovieTypeEntity movie_type_id) {
        this.movieTypeEntity = movie_type_id;
    }

    public Integer getMovie_duration() {
        return movie_duration;
    }

    public void setMovie_duration(Integer movie_duration) {
        this.movie_duration = movie_duration;
    }

    public String getMovie_schedule() {
        return movie_schedule;
    }

    public void setMovie_schedule(String movie_schedule) {
        this.movie_schedule = movie_schedule;
    }

    @Override
    public String toString() {
        return "= = = = = = = =  \n" +
                "Buy Tickets For Movie: " + movie_id +
                ", Title: " + movie_title +
                ", Type: " + movieTypeEntity +
                ", Duration: " + movie_duration +
                ", Schedule: " + movie_schedule;
    }
}
