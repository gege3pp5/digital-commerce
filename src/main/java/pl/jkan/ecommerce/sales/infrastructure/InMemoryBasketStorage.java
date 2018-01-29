package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;

import java.util.HashMap;

public class InMemoryBasketStorage implements BasketStorage {
    private HashMap<Identifier, Basket> baskets;

    public InMemoryBasketStorage() {
        this.baskets = new HashMap<Identifier, Basket>();
    }

    @Override
    public Basket loadForCustomer(Identifier id) {
        if (!baskets.containsKey(id)) {
            baskets.put(id, new Basket());
        }

        return baskets.get(id);
    }

    @Override
    public void store(Identifier id, Basket basket) {
        baskets.put(id, basket);
    }
}
