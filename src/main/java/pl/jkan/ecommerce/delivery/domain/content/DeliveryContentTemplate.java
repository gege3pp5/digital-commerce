package pl.jkan.ecommerce.delivery.domain.content;

import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.ecommerce.delivery.domain.preparation.DeliveryPackage;

public interface DeliveryContentTemplate {

    public String renderContent(DeliveryPackage deliverySubject);
    public String renderSubject(DeliveryPackage deliverySubject);
}
