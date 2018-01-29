package pl.jkan.ecommerce.sales.domain.basket;

import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductNotAvailableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Basket {
    private final HashMap<Product, Integer> productQuantities;

    public Basket() {
       this.productQuantities = new HashMap<>();
    }

    public void add(Product product) throws ProductNotAvailableException {

        if (!product.isInStock()) {
            throw new ProductNotAvailableException();
        }

        if (!isInBasket(product)) {
            putToBasket(product);
        } else {
            increaseQuantity(product);
        }
    }

    public List<BasketItem> getReservedProducts() {
        ArrayList<BasketItem> items = new ArrayList<>(productQuantities.size());

        productQuantities.forEach((product, quantity) -> items.add(new BasketItem(product.getId(), quantity, product.getPrice())));

        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty() {
        return productQuantities.size() == 0;
    }

    public int productsCount() {
        return productQuantities.size();
    }

    private void putToBasket(Product product) {
        productQuantities.put(product, 1);
    }

    private void increaseQuantity(Product product) {
        productQuantities.put(product, productQuantities.get(product) + 1);
    }

    private boolean isInBasket(Product product) {
        return productQuantities.containsKey(product);
    }

    public void clear() {
        this.productQuantities.clear();
    }
}
