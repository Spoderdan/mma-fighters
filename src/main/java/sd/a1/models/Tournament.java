package sd.a1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Date;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Date date;
    private String filled;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFilled() {
        return filled;
    }

    public void setFilled(String filled) {
        this.filled = filled;
    }

    public static Tournament findById(List<Tournament> tournaments, Integer id) {
        return tournaments.stream().filter(tournament -> id.equals(tournament.getId())).findFirst().orElse(null);
    }

}
