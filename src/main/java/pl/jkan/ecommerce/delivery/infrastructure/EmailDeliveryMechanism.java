package pl.jkan.ecommerce.delivery.infrastructure;

import pl.jkan.ecommerce.delivery.domain.DeliveryMechanism;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.support.smtp.Mailer;
import pl.jkan.support.smtp.MailerException;

public class EmailDeliveryMechanism implements DeliveryMechanism {
    private Mailer mailer;
    private String sender;

    public EmailDeliveryMechanism(Mailer mailer, String sender) {
        this.mailer = mailer;
        this.sender = sender;
    }

    public EmailDeliveryMechanism(Mailer mailer) {
        this.mailer = mailer;
        this.sender = "no-reply@email.dev";
    }

    @Override
    public void handleDelivery(DeliverySubject deliverySubject) {
        try {
            mailer.send(deliverySubject.getRecipient(), deliverySubject.getSubject(), deliverySubject.getContent(), sender);
        } catch (MailerException e) {
        }
    }
}
