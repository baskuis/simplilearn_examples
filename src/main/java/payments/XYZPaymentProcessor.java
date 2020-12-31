package payments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class XYZPaymentProcessor {

    List<XYZPaymentApi> processors = new ArrayList<>();

    public XYZPaymentProcessor() {
        processors.add(new XYZPaymentApiImpl());
    }

    public void process() {
        processors.forEach((p) -> p.deposit(0F, "123", "abc"));
    }

}
