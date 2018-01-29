package pl.jkan.ecommerce.delivery.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface OrderSpecifications {
    public Specification get(Identifier orderId);
}
