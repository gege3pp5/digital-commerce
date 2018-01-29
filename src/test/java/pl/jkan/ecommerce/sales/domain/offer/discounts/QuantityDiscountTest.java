package pl.jkan.ecommerce.sales.domain.offer.discounts;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.offer.Discount;
import pl.jkan.ecommerce.sales.domain.offer.OfferItem;

public class QuantityDiscountTest {
    @Test
    public void itApplyDiscountBasedOnQuantity() {
        QuantityDiscount discount = new QuantityDiscount(5, 0.20);
        OfferItem item = new OfferItem(Identifier.generateUUID(), 5, 50.00);

        Discount d = discount.calculateDiscount(item);
        item.apply(d);

        Assert.assertTrue(40.00 == item.getTotalCost());
    }
}
