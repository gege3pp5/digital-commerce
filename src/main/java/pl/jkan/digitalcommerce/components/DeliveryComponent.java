package pl.jkan.digitalcommerce.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.delivery.application.OrderConfirmedListener;
import pl.jkan.ecommerce.delivery.domain.content.SimpleTemplate;
import pl.jkan.ecommerce.delivery.domain.preparation.DeliveryPreparation;
import pl.jkan.ecommerce.delivery.domain.preparation.KeySource;
import pl.jkan.ecommerce.delivery.domain.preparation.ProductDetail;
import pl.jkan.ecommerce.delivery.domain.preparation.ProductDetails;
import pl.jkan.ecommerce.delivery.infrastructure.*;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.ProductDto;
import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.support.smtp.SmtpConfiguration;
import pl.jkan.support.smtp.SmtpMailer;

@Component
public class DeliveryComponent {

    @Autowired
    EventBus eventBus;

    @Autowired
    OrderRepository orderRepository;

    @Bean
    private OrderConfirmedListener orderConfirmedListener() {

        OrderConfirmedListener listener = new OrderConfirmedListener(
                new EmailDeliveryMechanism(
                    new SmtpMailer(
                            new SmtpConfiguration(
                                    System.getenv("ECOMMERCE_EMAIL_SERVER"),
                                    System.getenv("ECOMMERCE_EMAIL_USER"),
                                    System.getenv("ECOMMERCE_EMAIL_PASSWORD"),
                                    System.getenv("ECOMMERCE_EMAIL_PORT")
                            )
                    ),
                    System.getenv("ECOMMERCE_EMAIL_SENDER")
                ),
                new OrderRepositoryOrderSpecification(orderRepository),
                new DeliveryPreparation(
                        productDetails(),
                        new InfinitiveKeySource()
                ),
                new SimpleTemplate(
                    "Your order __order_id__",
                    "Dear Customer",
                    "Your __product_name__ key is: __product_key__"
                )
        );
        eventBus.addEventListener(OrderConfirmed.class, listener);

        return listener;
    }

    @Bean
    ProductDetails productDetails() {
        InMemoryProductDetails productDetails = new InMemoryProductDetails();
        productDetails.add(new Identifier("p1"), new ProductDetail("Battlefield"));
        productDetails.add(new Identifier("p2"), new ProductDetail("Diablo III"));
        productDetails.add(new Identifier("p3"), new ProductDetail("Starcraft II"));

        return productDetails;
    }
}
