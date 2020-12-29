package dundermifflin.scanton;

import dundermifflin.DistributionCenter;
import dundermifflin.thingthatcangowrong.DeliveryUnavailableException;

public class ScantonDistributionCenter extends DistributionCenter {

    public DeliveryTruck deliveryTruck = new DeliveryTruck();

    public void shipProduct(Product product) throws DeliveryUnavailableException {
        deliveryTruck.deliver(product);
    }

    public static class DeliveryTruck {
        boolean isBroken = true;
        public void deliver(DistributionCenter.Product product) {
            if (isBroken) {
                throw new DeliveryUnavailableException("Truck is broken");
            }
            System.out.println("Truck delivering product " + product.name);
        }
        public void setBroken(boolean broken) {
            isBroken = broken;
        }
    }

}
