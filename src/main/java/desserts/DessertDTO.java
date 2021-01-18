package desserts;

public class DessertDTO {

    Long id;
    String name;
    boolean good;

    public DessertDTO(Long id, String name, boolean good) {
        this.id = id;
        this.name = name;
        this.good = good;
    }

    public DessertDTO(String name, boolean good) {
        this.name = name;
        this.good = good;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public static int compareByName(DessertDTO a, DessertDTO b) {
        return a.name.compareTo(b.name);
    }

    @Override
    public String toString() {
        return "DessertDTO{" +
                "name='" + name + '\'' +
                ", good=" + good +
                '}';
    }

}
