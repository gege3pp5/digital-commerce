package pl.jkan.ecommerce.delivery.domain.preparation;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryPreparation {
    private ProductDetails productDetails;
    private KeySource keySource;

    public DeliveryPreparation(ProductDetails productDetails, KeySource keySource) {
        this.productDetails = productDetails;
        this.keySource = keySource;
    }

    public DeliveryPackage prepare(Identifier orderId, List<Identifier> orderedProducts) {

        List<Product> products = orderedProducts.stream()
                .map(productId -> {
                    return new Product(
                            productDetails.get(productId).getName(),
                            keySource.nextForProduct(productId)
                    );
                })
                .collect(Collectors.toList())
        ;

        return new DeliveryPackage(orderId, products);
    }
}
