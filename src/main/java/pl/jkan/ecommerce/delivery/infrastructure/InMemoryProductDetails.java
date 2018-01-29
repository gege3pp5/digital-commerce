package pl.jkan.ecommerce.delivery.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.preparation.ProductDetail;
import pl.jkan.ecommerce.delivery.domain.preparation.ProductDetails;

import java.util.HashMap;

public class InMemoryProductDetails implements ProductDetails {
    private HashMap<Identifier, ProductDetail> products = new HashMap<>();

    @Override
    public ProductDetail get(Identifier id) {
        return products.get(id);
    }

    public void add(Identifier id, ProductDetail detail) {
        products.put(id, detail);
    }
}
