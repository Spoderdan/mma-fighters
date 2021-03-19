package sd.a1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venue {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer fighter;
    private Integer tournament;

    public Integer getFighter() {
        return fighter;
    }

    public void setFighter(Integer fighter) {
        this.fighter = fighter;
    }

    public Integer getTournament() {
        return tournament;
    }

    public void setTournament(Integer tournament) {
        this.tournament = tournament;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
