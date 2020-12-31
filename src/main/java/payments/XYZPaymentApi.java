package payments;

/**
 * Point is that this provides clarity
 *
 */
public interface XYZPaymentApi {

    /** One team works on this */
    void deposit(Float amount, String accountID, String signature);

    /** One team works on this */
    void withdraw(Float amount, String accountID, String signature);

}
