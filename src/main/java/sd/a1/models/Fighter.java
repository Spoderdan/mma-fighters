package sd.a1.models;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "fighter")
public class Fighter {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "weight_class")
    private String weightClass;
    private String quarantined;
    private String matched;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    public String getQuarantineStatus() {
        return quarantined;
    }

    public void setQuarantineStatus(String quarantineStatus) {
        this.quarantined = quarantineStatus;
    }

    public static Fighter findById(List<Fighter> fighters, Integer id) {
        return fighters.stream().filter(fighter -> id.equals(fighter.getId())).findFirst().orElse(null);
    }

    public String getMatched() {
        return matched;
    }

    public void setMatched(String matched) {
        this.matched = matched;
    }

    public String test(){
        Random random = new Random();
        int nr = random.nextInt(5);
        if(nr == 1)
            return "Positive";
        else
            return "Negative";
    }



}
