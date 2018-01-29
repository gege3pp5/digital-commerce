package pl.jkan.ecommerce.sales.infrastructure;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;

public class InMemoryBasketStorageTest {

    @Test
    public void itCreateEmptyBasketForClient() {
        BasketStorage storage = new InMemoryBasketStorage();
        Basket b = storage.loadForCustomer(new Identifier("c_1"));

        Assert.assertNotNull(b);
        Assert.assertTrue(b.isEmpty());
    }

    @Test
    public void itStoreBasketsForClients() {
        BasketStorage storage = new InMemoryBasketStorage();
        Basket b1 = storage.loadForCustomer(new Identifier("c_1"));
        Basket b2 = storage.loadForCustomer(new Identifier("c_2"));

        Assert.assertNotEquals(b1, b2);
    }

    @Test
    public void itStoreCustomerBasket() {
        BasketStorage storage = new InMemoryBasketStorage();
        Basket b = storage.loadForCustomer(new Identifier("c_1"));

        b.add(new Product(new Identifier("p_1"), 10));
        b.add(new Product(new Identifier("p_2"), 1));

        Basket same = storage.loadForCustomer(new Identifier("c_1"));
        Assert.assertEquals(2, same.productsCount());
    }
}
