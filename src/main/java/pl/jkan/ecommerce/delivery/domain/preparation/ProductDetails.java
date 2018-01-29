package pl.jkan.ecommerce.delivery.domain.preparation;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.preparation.ProductDetail;

public interface ProductDetails {
    public ProductDetail get(Identifier id);
}
