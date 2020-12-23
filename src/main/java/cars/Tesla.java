package cars;

public class Tesla extends Car {

    public Tesla(String vin) {
        super(vin);
        electric = true;
    }

    String model = "tesla";

    public String getModel() {
        return model;
    }
}
