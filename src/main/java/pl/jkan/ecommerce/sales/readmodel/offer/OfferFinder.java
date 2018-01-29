package pl.jkan.ecommerce.sales.readmodel.offer;

import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.offer.DiscountFactory;
import pl.jkan.ecommerce.sales.domain.offer.Offer;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.system.SystemUserContext;

public class OfferFinder {

    private BasketStorage basketStorage;
    private OfferMaker offerMaker;
    private SystemUserContext userContext;

    public OfferFinder(BasketStorage basketStorage, OfferMaker offerMaker, SystemUserContext userContext) {
        this.basketStorage = basketStorage;
        this.offerMaker = offerMaker;
        this.userContext = userContext;
    }

    public OfferDto currentOffer() {
            Offer offer = offerMaker.calculateOffer(
                    basketStorage.loadForCustomer(userContext.getCurrentUser().getId()).getReservedProducts(),
                    DiscountFactory.offerDiscount()
            );

            return new OfferDto(offer.getTotal(), offer.getItems().size());
    }
}
