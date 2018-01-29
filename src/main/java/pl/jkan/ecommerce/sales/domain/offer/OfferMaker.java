package pl.jkan.ecommerce.sales.domain.offer;

import pl.jkan.ecommerce.sales.domain.basket.BasketItem;

import java.util.List;
import java.util.stream.Collectors;

public class OfferMaker {
    public Offer calculateOffer(List<BasketItem> selectedItems) {

        List<OfferItem> offerItems = selectedItems.stream()
                .map(this::createOrderRow)
                .collect(Collectors.toList())
        ;

        return new Offer(offerItems);
    }

    public Offer calculateOffer(List<BasketItem> selectedItems, DiscountPolicy discountPolicy) {

        List<OfferItem> offerItems = selectedItems.stream()
                .map(this::createOrderRow)
                .collect(Collectors.toList())
        ;

        offerItems
            .forEach(item -> item.apply(discountPolicy.calculateDiscount(item)))
        ;

        return new Offer(offerItems);
    }

    private OfferItem createOrderRow(BasketItem basketItem) {
        OfferItem item = new OfferItem(
            basketItem.getId(),
            basketItem.getQuantity(),
            basketItem.getPrice() * basketItem.getQuantity()
        );

        return item;
    }
}
