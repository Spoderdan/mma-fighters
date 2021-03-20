package sd.a1.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "test_result")
public class TestResult {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "fighter_id")
    private Fighter fighterId;
    private String status;
    private Date date;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Fighter getFighterId() {
        return fighterId;
    }

    public void setFighterId(Fighter fighterId) {
        this.fighterId = fighterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
