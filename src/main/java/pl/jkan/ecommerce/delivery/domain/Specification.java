package pl.jkan.ecommerce.delivery.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

import java.util.List;

public class Specification {
    private Identifier id;
    private List<Identifier> products;
    private String email;

    public Specification(Identifier id, List<Identifier> products, String email) {
        this.id = id;
        this.products = products;
        this.email = email;
    }

    public Identifier getId() {
        return id;
    }

    public List<Identifier> getProducts() {
        return products;
    }

    public String getEmail() {
        return email;
    }
}
