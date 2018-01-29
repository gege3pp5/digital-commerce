package pl.jkan.ecommerce.sales.domain.offer;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class OfferItemTest {

    @Test
    public void itAllowApplyDiscountForItem() {
        OfferItem item = new OfferItem(new Identifier("p1"), 4, 10.00);
        item.apply(new Discount("static reduce", 5.00));

        Assert.assertTrue("Should match reduced price", item.getTotalCost() == 5.00);
    }

    @Test
    public void itReducePriceToMax0() {
        OfferItem item = new OfferItem(new Identifier("p1"), 4, 10.00);
        item.apply(new Discount("static reduce", 100.00));

        Assert.assertTrue("Should match reduced price", item.getTotalCost() == 0);
    }
}
