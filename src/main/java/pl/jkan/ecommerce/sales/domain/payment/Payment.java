package pl.jkan.ecommerce.sales.domain.payment;

import pl.jkan.ecommerce.canonicalmodel.Entity;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.order.ClientData;
import pl.jkan.ecommerce.sales.domain.payment.exception.NotRegisterdException;

public class Payment implements Entity {

    public enum PaymentStatus {
        PENDING,
        CONFIRMED
    }

    private Identifier id;
    private ClientData clientData;
    private Double amount;
    private String token;
    private PaymentStatus status;

    public Payment(Identifier id, ClientData clientData, Double amount) {
        this.id = id;
        this.clientData = clientData;
        this.amount = amount;
        this.token = token;
        this.status = PaymentStatus.PENDING;
    }

    public static Payment createPayment(ClientData clientData, Double amount) {
        return new Payment(Identifier.generateUUID(), clientData, amount);
    }

    public boolean isPending() {
        return status == PaymentStatus.PENDING;
    }

    public boolean isConfirmed() {
        return status == PaymentStatus.CONFIRMED;
    }

    public Double getValue() {
        return amount;
    }

    public Integer expressValueInSmallestUnit() {
        return (new Double(amount * 100).intValue());
    }

    public ClientData getClientData() {
        return clientData;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public void register(PaymentGateway gateway) {
        token = gateway.obtainPaymentToken(this);
    }

    public String getPaymentToken() {

        if (token == null) {
            throw new NotRegisterdException();
        }

        return token;
    }

    public void confirm() {
        this.status = PaymentStatus.CONFIRMED;
    }
}
