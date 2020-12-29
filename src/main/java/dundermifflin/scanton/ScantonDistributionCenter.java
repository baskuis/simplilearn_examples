package dundermifflin.scanton;

import dundermifflin.DistributionCenter;

public class ScantonDistributionCenter extends DistributionCenter {

    DeliveryTruck deliveryTruck = new DeliveryTruck();

    public void shipProduct(Product product) {
        deliveryTruck.deliver(product);
    }

    static class DeliveryTruck {
        final int maxWeight = 2000;
        public void deliver(DistributionCenter.Product product) {
            System.out.println("Truck delivering product " + product.name);
        }
    }

}
