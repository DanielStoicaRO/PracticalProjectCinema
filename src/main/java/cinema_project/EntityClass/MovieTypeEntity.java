package cinema_project.EntityClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Movie_Type_Entity")
public class MovieTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movie_type_id;

    private String movie_type;

    @OneToMany(mappedBy = "movieTypeEntity")
    private List<MoviesEntity> moviesEntityList;

    public Integer getMovie_type_id() {
        return movie_type_id;
    }

    public void setMovie_type_id(Integer movie_type_id) {
        this.movie_type_id = movie_type_id;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    @Override
    public String toString() {
        return "movie_type = " + movie_type;
    }
}
