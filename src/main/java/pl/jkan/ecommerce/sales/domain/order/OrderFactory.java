package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.payment.PaymentGateway;
import pl.jkan.ecommerce.sales.domain.offer.Offer;
import pl.jkan.ecommerce.sales.domain.payment.Payment;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFactory {

    private PaymentGateway paymentGateway;

    public OrderFactory(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Order create(Identifier id, Offer offer, ClientData clientData) {

        if (offer.getItems().isEmpty()) {
            throw new OrderMustContainsItemsException();
        }

        List<OrderItem> items = offer.getItems()
                .stream()
                .map(item -> new OrderItem(item.getProductId(), item.getQuantity(), item.getTotalCost()))
                .collect(Collectors.toList())
        ;

        Payment payment = Payment.createPayment(clientData, offer.getTotal());
        payment.register(paymentGateway);

        return new Order(id, items, clientData, payment);
    }
}
