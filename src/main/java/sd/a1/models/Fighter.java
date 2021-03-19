package sd.a1.models;

import javax.persistence.*;

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

    public Fighter(String lastName, String firstName, String weightClass) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.weightClass = weightClass;
    }

    public Fighter() {
    }

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

    @Override
    public String toString() {
        return "Fighter{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", weightClass='" + weightClass + '\'' +
                '}';
    }
}
