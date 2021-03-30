package sd.a1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venue {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer fighter1;
    private Integer fighter2;
    @Column(name = "tournament")
    private Integer match;

    public Integer getFighter1() {
        return fighter1;
    }

    public void setFighter1(Integer fighter1) {
        this.fighter1 = fighter1;
    }

    public Integer getFighter2() {
        return fighter2;
    }

    public void setFighter2(Integer fighter2) {
        this.fighter2 = fighter2;
    }

    public Integer getMatch() {
        return match;
    }

    public void setMatch(Integer tournament) {
        this.match = tournament;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
