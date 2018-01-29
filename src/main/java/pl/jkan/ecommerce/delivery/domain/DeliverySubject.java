package pl.jkan.ecommerce.delivery.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class DeliverySubject {
    private Identifier orderId;
    private String content;
    private String subject;
    private String recipient;

    public DeliverySubject(Identifier orderId, String content, String subject, String recipient) {
        this.orderId = orderId;
        this.content = content;
        this.subject = subject;
        this.recipient = recipient;
    }

    public Identifier getOrderId() {
        return orderId;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipient() {
        return recipient;
    }
}
