package pl.jkan.ecommerce.canonicalmodel.events;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class OrderConfirmed {
    private Identifier orderId;

    public OrderConfirmed(Identifier orderId) {
        this.orderId = orderId;
    }

    public Identifier getOrderId() {
        return orderId;
    }
}
