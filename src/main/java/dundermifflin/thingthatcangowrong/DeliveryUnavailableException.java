package dundermifflin.thingthatcangowrong;

public class DeliveryUnavailableException extends RuntimeException {
    public DeliveryUnavailableException(String message) {
        super(message);
    }
}
