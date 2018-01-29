package pl.jkan.ecommerce.delivery.application;

import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.delivery.domain.DeliveryMechanism;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.ecommerce.delivery.domain.OrderSpecifications;
import pl.jkan.ecommerce.delivery.domain.Specification;
import pl.jkan.ecommerce.delivery.domain.content.DeliveryContentTemplate;
import pl.jkan.ecommerce.delivery.domain.preparation.DeliveryPackage;
import pl.jkan.ecommerce.delivery.domain.preparation.DeliveryPreparation;
import pl.jkan.ecommerce.system.Subscriber;

public class OrderConfirmedListener implements Subscriber<OrderConfirmed> {

    private DeliveryMechanism deliveryMechanism;
    private OrderSpecifications orderSpecifications;
    private DeliveryPreparation deliveryPreparation;
    private DeliveryContentTemplate contentTemplate;

    public OrderConfirmedListener(DeliveryMechanism deliveryMechanism, OrderSpecifications orderSpecifications, DeliveryPreparation deliveryPreparation, DeliveryContentTemplate contentTemplate) {
        this.deliveryMechanism = deliveryMechanism;
        this.orderSpecifications = orderSpecifications;
        this.deliveryPreparation = deliveryPreparation;
        this.contentTemplate = contentTemplate;
    }

    @Override
    public void handle(OrderConfirmed event) {
        Specification specification = orderSpecifications.get(event.getOrderId());

        DeliveryPackage deliveryPackage = deliveryPreparation.prepare(event.getOrderId(), specification.getProducts());

        DeliverySubject deliverySubject = new DeliverySubject(
                event.getOrderId(),
                contentTemplate.renderContent(deliveryPackage),
                contentTemplate.renderSubject(deliveryPackage),
                specification.getEmail()
        );

        deliveryMechanism.handleDelivery(deliverySubject);
    }
}
