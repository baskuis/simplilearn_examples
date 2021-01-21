package robot;

import javax.persistence.*;

@Table(name = "battery")
@Entity
public class BatteryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "percentageRemaining")
    private Integer percentagePower = 100;

    @OneToOne
    RobotEntity robotEntity;

    public RobotEntity getRobotEntity() {
        return robotEntity;
    }

    public void setRobotEntity(RobotEntity robotEntity) {
        this.robotEntity = robotEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getPercentagePower() {
        return percentagePower;
    }

    public void setPercentagePower(Integer percentagePower) {
        this.percentagePower = percentagePower;
    }
}
