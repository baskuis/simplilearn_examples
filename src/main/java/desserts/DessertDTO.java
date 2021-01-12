package desserts;

public class DessertDTO {

    String name;
    boolean good;

    public DessertDTO(String name, boolean good) {
        this.name = name;
        this.good = good;
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
