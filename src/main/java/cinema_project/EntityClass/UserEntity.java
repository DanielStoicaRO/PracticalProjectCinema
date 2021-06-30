package cinema_project.EntityClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String user_name;
    private String user_password;
    private String user_phone;

    //@OneToMany cu TicketsEntity
    @OneToMany(mappedBy = "userEntity")
    private List<TicketsEntity> ticketsEntityList;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public List<TicketsEntity> getTicketsEntityList() {
        return ticketsEntityList;
    }

    public void setTicketsEntityList(List<TicketsEntity> ticketsEntityList) {
        this.ticketsEntityList = ticketsEntityList;
    }
}
