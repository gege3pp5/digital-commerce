package pl.jkan.ecommerce.sales.domain.offer;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class OfferItem {
    private Identifier productId;
    private Integer quantity;
    private Double totalCost;
    private Discount discount;

    public OfferItem(Identifier productId, Integer quantity, Double totalCost) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.discount = Discount.noDiscount();
    }

    public Identifier getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalCost() {
        return (totalCost - discount.getValue()) <= 0 ? 0.00 : (totalCost - discount.getValue());
    }

    public void apply(Discount discount) {
        this.discount = discount;
    }
}
