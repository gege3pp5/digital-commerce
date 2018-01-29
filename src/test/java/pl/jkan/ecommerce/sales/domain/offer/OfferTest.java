package pl.jkan.ecommerce.sales.domain.offer;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;

import java.util.ArrayList;
import java.util.List;

public class OfferTest {
    @Test
    public void itCalculateTotalOfItems() {
        List<OfferItem> items = new ArrayList<>();
        items.add(new OfferItem(new Identifier("p1"), 2, 10.00));
        items.add(new OfferItem(new Identifier("p2"), 1, 10.00));

        Offer offer = new Offer(items);

        Assert.assertTrue(20.00 == offer.getTotal());
    }

    @Test
    public void itConsiderDiscountsWhenCalculateTotal() {
        List<OfferItem> items = new ArrayList<>();

        OfferItem discounted = new OfferItem(new Identifier("p1"), 2, 10.00);
        discounted.apply(new Discount("static reduce", 10.00));

        items.add(discounted);
        items.add(new OfferItem(new Identifier("p2"), 1, 10.00));
        Offer offer = new Offer(items);

        Assert.assertTrue(10.00 == offer.getTotal());
    }
}
