package pl.jkan.ecommerce.delivery.domain;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.preparation.*;

import java.util.ArrayList;

public class DeliveryPreparationTest {
    @Test
    public void itCreateDeliveryPackageBasedOnSpecification() {
        Identifier orderId = Identifier.generateUUID();
        ArrayList<Identifier> products = new ArrayList<>();
        products.add(new Identifier("p_1"));
        products.add(new Identifier("p_2"));
        products.add(new Identifier("p_2"));

        DeliveryPreparation preparation = new DeliveryPreparation(new ProductInformation(), new SameKeySource());
        DeliveryPackage deliveryPackage = preparation.prepare(orderId, products);

        Assert.assertEquals(deliveryPackage.getOrderId(), orderId);
        Assert.assertTrue(deliveryPackage.getProducts().size() == 3);

        deliveryPackage.getProducts().stream()
                .forEach(p -> {
                    Assert.assertTrue(p.getKey().equals("AAAAA-BBBB"));
                    Assert.assertTrue(p.getName().equals("some-name"));
                })
        ;
    }

    class SameKeySource implements KeySource {
        @Override
        public String nextForProduct(Identifier id) {
            return "AAAAA-BBBB";
        }
    }

    class ProductInformation implements ProductDetails {
        @Override
        public ProductDetail get(Identifier id) {
            return new ProductDetail("some-name");
        }
    }
}
