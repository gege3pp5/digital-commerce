package pl.jkan.digitalcommerce.components.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.services.payment.Przelewy24PaymentConfirmation;
import pl.jkan.ecommerce.sales.application.services.payment.Przelewy24PaymentGateway;
import pl.jkan.ecommerce.sales.domain.order.ClientInformation;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;
import pl.jkan.ecommerce.sales.domain.payment.PaymentGateway;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;
import pl.jkan.ecommerce.sales.infrastructure.*;
import pl.jkan.przelewy24.Przelewy24Api;
import pl.jkan.przelewy24.Przelewy24Properties;
import pl.jkan.support.http.NativeHttpClient;

@Component
public class SalesInfrastructureComponent {

    @Bean
    private ProductCatalog getProductCatalog() {
        ProductCatalog pc = new InMemoryProductCatalog();
        pc.add(new Product(new Identifier("p1"), 10, 10.00));
        pc.add(new Product(new Identifier("p2"), 10, 13.50));
        pc.add(new Product(new Identifier("p3"), 0, 10.00));

        return pc;
    }

    @Bean
    private InMemoryBasketStorage getBasketStorage() {
        return new InMemoryBasketStorage();
    }

    @Bean
    private ClientInformation clientInformation() {
        return new InMemoryClientInformation();
    }

    @Bean
    OrderRepository orderRepository() {
        return new InMemoryOrderRepository();
    }

    @Bean
    PaymentGateway paymentGateway() {
        return new Przelewy24PaymentGateway(
                api(),
                System.getenv("P24_RETURN_URL"),
                System.getenv("P24_STATUS_URL")
        );
    }

    @Bean
    Przelewy24PaymentConfirmation paymentConfirmation() {
        return new Przelewy24PaymentConfirmation(api());
    }

    @Bean
    Przelewy24Api api() {
        return new Przelewy24Api(
                new Przelewy24Properties(
                        System.getenv("P24_MERCHANT_ID"),
                        System.getenv("P24_MERCHANT_ID"),
                        System.getenv("P24_CRC")
                ),
                new NativeHttpClient()
        );
    }
}
