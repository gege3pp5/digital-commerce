package pl.jkan.ecommerce.sales.domain.order;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.basket.BasketItem;
import pl.jkan.ecommerce.sales.domain.offer.Offer;
import pl.jkan.ecommerce.sales.domain.offer.OfferItem;
import pl.jkan.ecommerce.sales.domain.order.ClientData;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderFactory;
import pl.jkan.ecommerce.sales.domain.order.OrderMustContainsItemsException;
import pl.jkan.ecommerce.sales.domain.payment.NullPaymentGateway;
import pl.jkan.ecommerce.sales.domain.payment.Payment;
import pl.jkan.ecommerce.sales.domain.payment.PaymentGateway;

import java.util.ArrayList;
import java.util.List;

public class OrderFactoryTest {

    @Test
    public void itVerifyIfThereAreItems() {
        OrderFactory orderFactory = new OrderFactory(new NullPaymentGateway());

        Offer offer = new Offer(new ArrayList<>());

        try {
            Order order = orderFactory.create(new Identifier("order_1"), offer, exampleClientData());
            Assert.fail("Not allow to create order without items");
        } catch (OrderMustContainsItemsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void itCalculate() {
        OrderFactory orderFactory = new OrderFactory(new NullPaymentGateway());

        Offer offer = new Offer(new ArrayList<>());

        try {
            Order order = orderFactory.create(new Identifier("order_1"), offer, exampleClientData());
            Assert.fail("Not allow to create order without items");
        } catch (OrderMustContainsItemsException e) {
            Assert.assertTrue(true);
        }
    }

    private ClientData exampleClientData() {
        ClientData client = new ClientData(new Identifier("jakub"), "jakub.kanclerz@gmail.com");

        return client;
    }
}
