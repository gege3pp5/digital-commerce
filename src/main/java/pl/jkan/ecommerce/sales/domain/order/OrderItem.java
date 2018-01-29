package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class OrderItem {
    private Identifier productId;
    private Integer quantity;
    private Double totalCost;

    public OrderItem(Identifier productId, Integer quantity, Double totalCost) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public Identifier getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalCost() {
        return totalCost;
    }
}
