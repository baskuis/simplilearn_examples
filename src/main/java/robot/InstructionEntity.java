package robot;

import javax.persistence.*;

@Table(name = "instruction")
@Entity
public class InstructionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    private RobotEntity robot;

    @Column
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RobotEntity getRobot() {
        return robot;
    }

    public void setRobot(RobotEntity robot) {
        this.robot = robot;
    }

}
