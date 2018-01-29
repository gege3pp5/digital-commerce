package pl.jkan.ecommerce.sales.domain.offer;

import pl.jkan.ecommerce.sales.domain.offer.discounts.QuantityDiscount;

public class DiscountFactory {
    public static DiscountPolicy offerDiscount() {
        return staticDiscount();
    }

    public static DiscountPolicy checkoutDiscount() {
        return staticDiscount();
    }

    private static DiscountPolicy staticDiscount() {
        return new QuantityDiscount(5, 0.20);
    }
}
