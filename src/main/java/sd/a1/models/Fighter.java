package sd.a1.models;

import javax.persistence.*;
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
    @Column(name = "health_status")
    private String healthStatus;

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

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void test(){
        Random random = new Random();
        if(random.nextBoolean())
            healthStatus = "Positive";
        else
            healthStatus = "Negative";
    }
}
