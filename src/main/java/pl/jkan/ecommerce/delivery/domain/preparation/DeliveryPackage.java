package pl.jkan.ecommerce.delivery.domain.preparation;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

import java.util.List;

public class DeliveryPackage {
    private Identifier orderId;
    private List<Product> products;

    public DeliveryPackage(Identifier orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
    }

    public Identifier getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }
}
