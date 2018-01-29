package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class VerifyPaymentCommand {
    private String paymentId;
    private String orderId;
    private String checkSum;

    public VerifyPaymentCommand(String paymentId, String orderId, String checkSum) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.checkSum = checkSum;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public Identifier getPaymentIdentifier() {
        return new Identifier(paymentId);
    }
}
