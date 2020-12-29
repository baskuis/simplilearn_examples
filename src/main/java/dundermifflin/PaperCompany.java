package dundermifflin;

import dundermifflin.newyork.NewYorkDistributionCenter;
import dundermifflin.scanton.ScantonDistributionCenter;

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
    }

    public void receiveOrders() {
        orders.add(new Order("paperstock","scranton", 200, 40.00F));
        orders.add(new Order("posters","newyork", 50, 40.00F));
        orders.add(new Order("printerpaper","scranton", 5000, 90.00F));
        orders.add(new Order("cardboard","newyork", 500, 00.00F));
    }

    public void processOrders() {
        for (Order order : orders) {
            this.distributionCenters.get(order.closestLocation).shipProduct(
                    new DistributionCenter.Product(
                            order.product,
                            order.size
                    )
            );
            revenueProcessed += order.price;
            orderProcessed++;
        }
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

        public String product;
        public String closestLocation;
        public int size = 0;
        public float price = 0;
    }

}
