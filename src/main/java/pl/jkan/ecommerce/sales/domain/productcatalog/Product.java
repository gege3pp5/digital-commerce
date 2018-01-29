package pl.jkan.ecommerce.sales.domain.productcatalog;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class Product {

    private Identifier id;
    private final Integer stockQty;
    private Double price;

    public Product(Identifier id, Integer stockQty, Double price) {
        this.id = id;
        this.stockQty = stockQty;
        this.price = price;
    }

    public Product(Identifier id, Integer stockQty) {
        this.id = id;
        this.stockQty = stockQty;
        this.price = 0.0;
    }

    public Product(Identifier id) {
        this.id = id;
        this.stockQty = 0;
    }

    public boolean isInStock() {
        return stockQty > 0;
    }

    public Identifier getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }
}
