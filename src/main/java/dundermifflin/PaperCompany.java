package dundermifflin;

import dundermifflin.newyork.NewYorkDistributionCenter;
import dundermifflin.scanton.ScantonDistributionCenter;
import dundermifflin.thingthatcangowrong.DeliveryRefusedException;
import dundermifflin.thingthatcangowrong.DeliveryUnavailableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaperCompany {

    Map<String, DistributionCenter> distributionCenters = new HashMap<String, DistributionCenter>();

    List<Order> orders = new ArrayList<Order>();

    Integer orderProcessed = 0;
    Float revenueProcessed = 0F;

    public PaperCompany() {
        distributionCenters.put("newyork", new NewYorkDistributionCenter());
        distributionCenters.put("scranton", new ScantonDistributionCenter());
    }

    public void receiveShipments() {
        distributionCenters.get("newyork").receiveShipment(new DistributionCenter.Product(
            "posters", 500
        ));
        distributionCenters.get("newyork").receiveShipment(new DistributionCenter.Product(
                "cardboard", 500
        ));
        distributionCenters.get("scranton").receiveShipment(new DistributionCenter.Product(
                "paperstock", 500
        ));
        distributionCenters.get("scranton").receiveShipment(new DistributionCenter.Product(
                "printerpaper", 500
        ));
        distributionCenters.get("newyork").receiveShipment(new DistributionCenter.Product(
                "kettlebells", 5
        ));
    }

    public void receiveOrders() {
        receiveOrder(new Order("rocket","mars", -4444, 0F));
        receiveOrder(new Order("paperstock","scranton", 200, 40.00F));
        receiveOrder(new Order("kettlebells","newyork", 500, 100.00F));
        receiveOrder(new Order("posters","newyork", 50, 40.00F));
        receiveOrder(new Order("printerpaper","scranton", 5000, 90.00F));
        receiveOrder(new Order("cardboard","newyork", 500, 05.00F));
    }

    private void receiveOrder(Order order) {
        try {
            orders.add(order);
        } catch (Exception e) {
            System.out.println("Unable to receive order. Message: " + e.getMessage());
        }
    }

    public void processOrders() {
        for (Order order : orders) {
            try {
                this.processOrder(order);
            } catch (DeliveryRefusedException e) {
                System.out.println("Delivery refused. Message: " + e.getMessage());
                System.out.println("Attempting to ship from scranton instead");
                order.closestLocation = "scranton";
                try {
                    this.processOrder(order);
                } catch (Exception error) {
                    System.out.println("Unable to try delivery again after shipping from scranton instead. Message: " + error.getMessage());
                }
            } catch (DeliveryUnavailableException e) {
                System.out.println("Delivery unavailable. Message: " + e.getMessage());

                System.out.println("Fixing the truck");
                ((ScantonDistributionCenter) distributionCenters.get("scranton"))
                        .deliveryTruck.setBroken(false);
                try {
                    System.out.println("Retrying the order");
                    this.processOrder(order);
                } catch (Exception error) {
                    System.out.println("Unable to try delivery again after fixing truck. Message: " + error.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Delivery not possible. Message: " + e.getMessage());
            } finally {
                System.out.println("========= moving on =========");
            }
        }
    }

    private void processOrder(Order order) throws DeliveryRefusedException, DeliveryUnavailableException {
        this.distributionCenters.get(order.closestLocation).shipProduct(
                new DistributionCenter.Product(
                        order.product,
                        order.size
                )
        );

        /** Order is processed */
        order.setProcessed(true);

        /** Needs to be accurate */
        revenueProcessed += order.price;
        orderProcessed++;

    }

    public void printSummary() {
        System.out.println("Processed " + orderProcessed + " orders");
        System.out.println("Today we had $" + revenueProcessed + " in revenue");
    }

    static class Order {

        public Order(String productName, String closestLocation, int size, float price) {
            this.product = productName;
            this.closestLocation = closestLocation;
            this.size = size;
            this.price = price;
        }

        boolean processed = false;
        public String product;
        public String closestLocation;
        public int size = 0;
        public float price = 0;

        public void setProcessed(boolean processed) {
            this.processed = processed;
        }

        public boolean isProcessed() {
            return processed;
        }
    }

}
