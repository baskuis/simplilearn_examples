package cars;

import java.io.Serializable;

public class Car implements Serializable {

    String vin;

    boolean electric = false;

    int age;

    public Car(String vin) {
        this.vin = vin;
    }

    boolean isNew = false;

    public boolean isElectric() {
        return electric;
    }

    public String getVin() {
        return vin;
    }

    public int getAge() {
        return age;
    }

    public boolean isNew() {
        return isNew;
    }
}
