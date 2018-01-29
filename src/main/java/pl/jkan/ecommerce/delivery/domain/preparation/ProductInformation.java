package pl.jkan.ecommerce.delivery.domain.preparation;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface ProductInformation {
    public ProductDetails get(Identifier productId);
}
