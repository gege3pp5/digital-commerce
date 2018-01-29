package pl.jkan.ecommerce.delivery.domain.content;

import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.ecommerce.delivery.domain.preparation.DeliveryPackage;

public class SimpleTemplate implements DeliveryContentTemplate {

    private String subject;
    private String header;
    private String itemLine;

    public SimpleTemplate(String subject, String header, String itemLine) {
        this.subject = subject;
        this.header = header;
        this.itemLine = itemLine;
    }

    @Override
    public String renderContent(DeliveryPackage deliverySubject) {
        String tmp = "";

        tmp += String.format("%s\n", header);

        String productLines = deliverySubject.getProducts().stream()
                .map(product -> {
                    return itemLine.replace("__product_name__", product.getName())
                            .replace("__product_key__", product.getKey())
                    ;
                })
                .map(line -> String.format("%s\n", line))
                .reduce((line, lines) -> lines + line)
                .orElse("")
        ;

        return tmp + productLines;
    }

    @Override
    public String renderSubject(DeliveryPackage deliverySubject) {
        return subject.replace("__order_id__", deliverySubject.getOrderId().toString());
    }
}
