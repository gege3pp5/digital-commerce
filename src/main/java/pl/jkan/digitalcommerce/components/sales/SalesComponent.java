package pl.jkan.digitalcommerce.components.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.api.AddToBasketHandler;
import pl.jkan.ecommerce.sales.application.api.ConfirmOrderHandler;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentHandler;
import pl.jkan.ecommerce.sales.application.services.payment.Przelewy24PaymentConfirmation;
import pl.jkan.ecommerce.sales.application.services.payment.Przelewy24PaymentGateway;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.sales.domain.order.OrderFactory;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;
import pl.jkan.ecommerce.sales.domain.payment.PaymentGateway;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketStorage;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryProductCatalog;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferFinder;
import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.ecommerce.system.SystemUserContext;
import pl.jkan.przelewy24.Przelewy24Api;
import pl.jkan.przelewy24.Przelewy24Properties;
import pl.jkan.support.http.NativeHttpClient;

@Component
public class SalesComponent {

    @Autowired SystemUserContext systemUserContext;

    @Autowired
    private BasketStorage basketStorage;

    @Autowired
    private ProductCatalog productCatalog;

    @Autowired
    private OfferMaker offerMaker;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentGateway paymentGateway;

    @Autowired
    private Przelewy24PaymentConfirmation paymentConfirmation;

    @Autowired
    private EventBus eventBus;

    @Bean
    private VerifyPaymentHandler verifyPaymentHandler() {
        return new VerifyPaymentHandler(
                paymentConfirmation,
                orderRepository,
                eventBus
        );
    }

    @Bean
    private AddToBasketHandler addToBasketHandler() {
        return new AddToBasketHandler(
            systemUserContext,
            basketStorage,
            productCatalog
        );
    }

    @Bean
    private ConfirmOrderHandler confirmOrderHandler() {
        return new ConfirmOrderHandler(
            orderRepository,
                offerMaker,
                basketStorage,
                systemUserContext,
                new OrderFactory(paymentGateway)
        );
    }

    @Bean
    private OfferMaker getOfferMaker() {
        return new OfferMaker();
    }
}
