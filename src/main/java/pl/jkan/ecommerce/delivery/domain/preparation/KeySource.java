package pl.jkan.ecommerce.delivery.domain.preparation;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface KeySource {
    public String nextForProduct(Identifier id);
}
