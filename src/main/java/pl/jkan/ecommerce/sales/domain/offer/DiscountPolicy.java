package pl.jkan.ecommerce.sales.domain.offer;

import pl.jkan.ecommerce.sales.domain.offer.Discount;
import pl.jkan.ecommerce.sales.domain.offer.OfferItem;

public interface DiscountPolicy {
    public Discount calculateDiscount(OfferItem item);
}
