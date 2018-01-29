package pl.jkan.ecommerce.delivery.domain;

public interface DeliveryMechanism {
    public void handleDelivery(DeliverySubject deliverySubject);
}
