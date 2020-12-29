package dundermifflin.newyork;

import dundermifflin.DistributionCenter;
import dundermifflin.thingthatcangowrong.DeliveryRefusedException;

public class NewYorkDistributionCenter extends DistributionCenter {

    DeliveryBicycle deliveryBicycle = new DeliveryBicycle();

    @Override
    public void shipProduct(DistributionCenter.Product product) {
        deliveryBicycle.deliver(product);
    }

    static class DeliveryBicycle {
        void deliver(DistributionCenter.Product product) {
            if (product.name.equals("kettlebells")) {
                System.out.println("Not delivering heavy product");
                throw new DeliveryRefusedException("Not delivering heavy product");
            }
            System.out.println("Bicycle delivering product " + product.name);
        }
    }
}
