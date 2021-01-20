package robot;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "robot")
@Entity
public class RobotEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "switched_on")
    private Boolean switchedOn;

    @Column(name = "is_evil")
    private Boolean isEvil;

    public Boolean getEvil() {
        return isEvil;
    }

    public void setEvil(Boolean evil) {
        isEvil = evil;
    }

    public Boolean getSwitchedOn() {
        return switchedOn;
    }

    public void setSwitchedOn(Boolean switchedOn) {
        this.switchedOn = switchedOn;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
