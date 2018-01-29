package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class ConfirmOrderCommand {

    private Identifier orderId;
    private String deliveryEmail;

    public ConfirmOrderCommand(Identifier orderId, String deliveryEmail) {
        this.orderId = orderId;
        this.deliveryEmail = deliveryEmail;
    }

    public Identifier getOrderId() {
        return orderId;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }
}
