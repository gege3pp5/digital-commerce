package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class AddToBasketCommand {
    private Identifier productId;

    public AddToBasketCommand(Identifier productId) {
        this.productId = productId;
    }

    public Identifier getProductId() {
        return productId;
    }
}
