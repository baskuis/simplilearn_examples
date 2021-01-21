package robot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> parts = new HashMap<>();

    @OneToOne(fetch = FetchType.EAGER)
    private BatteryEntity batteryEntity;

    @OneToMany(fetch = FetchType.EAGER)
    private List<InstructionEntity> instructions;

    @Embedded
    private Description description;

    public List<InstructionEntity> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionEntity> instructions) {
        this.instructions = instructions;
    }

    public BatteryEntity getBatteryEntity() {
        return batteryEntity;
    }

    public void setBatteryEntity(BatteryEntity batteryEntity) {
        this.batteryEntity = batteryEntity;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public static class Description {

        @Column(name = "description_id")
        Long id;
        String label;

        public Description() { }

        public Description(Long id, String label) {
            this.id = id;
            this.label = label;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public Map<String, String> getParts() {
        return parts;
    }

    public void setParts(Map<String, String> parts) {
        this.parts = parts;
    }

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

    public String describeRobot() {
        return String.format("name: %s, \n battery: %s \n first instruction: %s",
                this.name,
                this.batteryEntity.getPercentagePower(),
                this.instructions.stream().findFirst().get().getCommand()
        );
    }
}
