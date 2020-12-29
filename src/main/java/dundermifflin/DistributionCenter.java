package dundermifflin;

import java.util.ArrayList;
import java.util.List;

public abstract class DistributionCenter {

    List<Product> productOnHand = new ArrayList<Product>();

    public static class Product {
        public String name;
        public int qualityOnHand = 0;
        public Product(String name, int quantity) {
            this.name = name;
            this.qualityOnHand = quantity;
        }
    }

    public void receiveShipment(Product product) {
        if (!this.productOnHand.contains(product)) {
            this.productOnHand.add(product);
        }
        for(int i = 0; i < productOnHand.size(); i++) {
            if (productOnHand.get(i).name.equals(product.name)) {
                productOnHand.get(i).qualityOnHand += product.qualityOnHand;
            }
        }
    }

    public abstract void shipProduct(Product product);

}
