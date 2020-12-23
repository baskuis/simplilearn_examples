package cars;

public class Toyota extends Car {

    public Toyota(String vin) {
        super(vin);
        electric = false;
    }

    String model = "toyota";

    public String getModel() {
        return model;
    }

}
