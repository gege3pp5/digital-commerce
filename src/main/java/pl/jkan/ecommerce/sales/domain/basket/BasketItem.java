package pl.jkan.ecommerce.sales.domain.basket;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class BasketItem {
    private Identifier id;
    private Integer quantity;
    private Double price;

    public BasketItem(Identifier id, Integer quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Identifier getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }
}
