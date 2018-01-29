package pl.jkan.ecommerce.delivery.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.delivery.application.OrderConfirmedListener;
import pl.jkan.ecommerce.delivery.domain.DeliveryMechanism;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.ecommerce.delivery.domain.OrderSpecifications;
import pl.jkan.ecommerce.delivery.domain.Specification;
import pl.jkan.ecommerce.delivery.domain.content.SimpleTemplate;
import pl.jkan.ecommerce.delivery.domain.preparation.*;

import java.util.ArrayList;
import java.util.List;

public class OrderDeliveryTest {
    SpyDeliveryMechanism spyDeliveryMechanism;
    OrderConfirmedListener orderConfirmedListener;
    OrderSpecification orderSpecification;
    Identifier orderId;

    @Before
    public void setUp() {
        spyDeliveryMechanism = new SpyDeliveryMechanism();
        orderSpecification = new OrderSpecification();
        orderId = Identifier.generateUUID();
        orderConfirmedListener = new OrderConfirmedListener(
                spyDeliveryMechanism,
                orderSpecification,
                new StaticDeliveryPreparation(),
                new SimpleTemplate(
                "Your order __order_id__",
                "Dear Customer",
                "Your __product_name__ key is: __product_key__"
                )
        );
    }

    @Test
    public void itPerformDelivery() {
        orderConfirmedListener.handle(new OrderConfirmed(orderId));

        Assert.assertNotNull(spyDeliveryMechanism.lastSubject);
    }

    @Test
    public void itCollectOrderedProducts() {
        orderContains(new Identifier("diablo"), 2);
        orderContains(new Identifier("cs-go"), 5);

        orderConfirmedListener.handle(new OrderConfirmed(orderId));

        DeliverySubject deliverySubject = spyDeliveryMechanism.lastSubject;
        Assert.assertTrue(deliverySubject.getContent().contains("diablo"));
        Assert.assertTrue(deliverySubject.getContent().contains("cs-go"));
        Assert.assertTrue(deliverySubject.getContent().contains("AAA-BBB"));
        Assert.assertTrue(deliverySubject.getContent().contains("Dear Customer"));

        Assert.assertTrue(deliverySubject.getSubject().contains(orderId.toString()));
    }

    private void orderContains(Identifier productId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            orderSpecification.products.add(productId);
        }
    }

    class OrderSpecification implements OrderSpecifications {
        public ArrayList<Identifier> products = new ArrayList<>();
        @Override
        public Specification get(Identifier orderId) {
            return new Specification(orderId, products, "email@email.dev");
        }
    }


    class SpyDeliveryMechanism implements DeliveryMechanism {
        DeliverySubject lastSubject;
        @Override
        public void handleDelivery(DeliverySubject deliverySubject) {
            lastSubject = deliverySubject;
        }
    }

    class StaticDeliveryPreparation extends DeliveryPreparation {

        public StaticDeliveryPreparation() {
            super(
                    new ProductDetails() {
                        @Override
                        public ProductDetail get(Identifier id) {
                            return new ProductDetail(id.toString());
                        }
                    },
                    new KeySource() {
                        @Override
                        public String nextForProduct(Identifier id) {
                            return "AAA-BBB";
                        }
                    }
            );
        }

        @Override
        public DeliveryPackage prepare(Identifier orderId, List<Identifier> orderedProducts) {
            return super.prepare(orderId, orderedProducts);
        }
    }
}
