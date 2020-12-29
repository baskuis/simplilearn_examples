package dundermifflin.newyork;

import dundermifflin.DistributionCenter;

public class NewYorkDistributionCenter extends DistributionCenter {

    DeliveryBicycle deliveryBicycle = new DeliveryBicycle();

    @Override
    public void shipProduct(DistributionCenter.Product product) {
        deliveryBicycle.deliver(product);
    }

    static class DeliveryBicycle {
        final int maxWeight = 20;
        void deliver(DistributionCenter.Product product) {
            System.out.println("Bicycle delivering product " + product.name);
        }
    }
}
