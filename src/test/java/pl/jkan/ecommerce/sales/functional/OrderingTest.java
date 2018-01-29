package pl.jkan.ecommerce.sales.functional;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.api.ConfirmOrderCommand;
import pl.jkan.ecommerce.sales.application.api.ConfirmOrderHandler;
import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderFactory;
import pl.jkan.ecommerce.sales.domain.order.OrderItem;
import pl.jkan.ecommerce.sales.domain.payment.NullPaymentGateway;
import pl.jkan.ecommerce.sales.domain.payment.Payment;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketStorage;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryOrderRepository;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryProductCatalog;
import pl.jkan.ecommerce.system.infrastructure.InMemorySystemUserContext;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OrderingTest {

    private final InMemoryProductCatalog productCatalog;
    private final InMemoryBasketStorage basketStorage;
    private final ConfirmOrderHandler confirmOrderHandler;
    private final InMemoryOrderRepository orderRepository;
    private InMemorySystemUserContext systemUserContext;

    private Identifier orderId;

    public OrderingTest() {
        this.systemUserContext = new InMemorySystemUserContext();
        this.productCatalog = new InMemoryProductCatalog();
        this.basketStorage = new InMemoryBasketStorage();
        this.orderRepository = new InMemoryOrderRepository();
        this.confirmOrderHandler = new ConfirmOrderHandler(
                this.orderRepository,
                new OfferMaker(),
                this.basketStorage,
                this.systemUserContext,
                new OrderFactory(new NullPaymentGateway())
        );
    }

    @Test
    public void itAllowOrderSelectedProducts() {
        iAmGuestBuyerIdentifiedWith(new Identifier("customer_1"));
        selectedProduct(new Identifier("p1"));
        selectedProduct(new Identifier("p2"));

        confirmReservation();

        thereIsPendingOrderWithId(orderId);
        orderContainsProduct(new Identifier("p1"));
        orderContainsProduct(new Identifier("p2"));
        orderIsWaitingForPayment(orderId);
        thereIsPendingPayment(orderId, 20.00);
        thereIsEmptyBasketAfterAll();
    }

    private void thereIsEmptyBasketAfterAll() {
        Basket b = basketStorage.loadForCustomer(new Identifier("customer_1"));
        Assert.assertTrue(b.isEmpty());
    }

    private void thereIsPendingPayment(Identifier orderId, double money) {
        Order order = this.orderRepository.load(orderId);
        Payment payment = order.getPayment();

        Assert.assertTrue(payment.isPending());
        Assert.assertTrue("Payment should equal appropriate value", payment.getValue() == money);
    }

    private void orderIsWaitingForPayment(Identifier orderId) {
        Order order = this.orderRepository.load(orderId);
        Assert.assertFalse("Order should not be paid", order.isPaid());
        Assert.assertNotNull(order.getPayment());
    }

    private void orderContainsProduct(Identifier p) {
        Order order = this.orderRepository.load(orderId);

        thereIsFollowingProductInOrderedItems(p, order.getItems());
    }

    private void thereIsFollowingProductInOrderedItems(Identifier productId, Collection<OrderItem> items) {
        List<OrderItem> ordered = items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .collect(Collectors.toList())
        ;

        Assert.assertFalse(ordered.isEmpty());
    }

    private void thereIsPendingOrderWithId(Identifier orderId) {

        try {
            Order order = this.orderRepository.load(orderId);
            Assert.assertTrue(order.getId().equals(orderId));
        } catch (RuntimeException e) {
            Assert.fail("There is no such order");
        }
    }

    private void confirmReservation() {
        orderId = Identifier.generateUUID();
        confirmOrderHandler.handle(new ConfirmOrderCommand(orderId, "delivery@email.dev"));
    }

    private void selectedProduct(Identifier p1) {
        Basket basket = basketStorage.loadForCustomer(new Identifier("customer_1"));
        basket.add(new Product(p1, 10, 10.00));
    }

    private void iAmGuestBuyerIdentifiedWith(Identifier customer) {
        systemUserContext.authenticate(customer);
    }
}
