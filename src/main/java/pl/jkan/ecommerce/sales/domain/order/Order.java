package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Entity;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.offer.Offer;
import pl.jkan.ecommerce.sales.domain.payment.Payment;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Order implements Entity {
    private Identifier id;

    private List<OrderItem> items;
    private ClientData clientData;
    private Payment payment;

    public Order(Identifier id, List<OrderItem> items, ClientData clientData, Payment payment) {
        this.id = id;
        this.items = items;
        this.clientData = clientData;
        this.payment = payment;
    }

    public Order(Identifier id, Offer offer) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    public Collection<OrderItem> getItems() {
        return (Collection<OrderItem>) Collections.unmodifiableCollection(items);
    }

    public Boolean isPaid() {
        return payment.isConfirmed();
    }

    public Payment getPayment() {
        return payment;
    }

    public void confirm() {
        this.payment.confirm();
    }

    public ClientData getClientData() {
        return clientData;
    }
}
