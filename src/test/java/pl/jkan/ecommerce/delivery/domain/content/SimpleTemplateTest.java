package pl.jkan.ecommerce.delivery.domain.content;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.preparation.DeliveryPackage;
import pl.jkan.ecommerce.delivery.domain.preparation.Product;

import java.util.ArrayList;

public class SimpleTemplateTest {
    @Test
    public void itAllowGenerateSubjectBasedOnDeliveryPackage() {
        SimpleTemplate template = new SimpleTemplate(
                "Your order __order_id__",
                "",
                ""
        );

        String subject = template.renderSubject(deliveryPackage());

        Assert.assertEquals("Your order id_123", subject);
    }

    @Test
    public void itAllowGenerateContentBasedOnDeliveryPackage() {
        SimpleTemplate template = new SimpleTemplate(
                "Your order __order_id__",
                "Dear Customer",
                "Your __product_name__ key is: __product_key__"
        );

        String content = template.renderContent(deliveryPackage());

        Assert.assertTrue(content.contains("Dear Customer\n"));
        Assert.assertTrue(content.contains("Your diablo key is: ABC-123\n"));
        Assert.assertTrue(content.contains("Your cs-go key is: DEF-123\n"));
    }

    private DeliveryPackage deliveryPackage() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("diablo", "ABC-123"));
        products.add(new Product("cs-go", "DEF-123"));
        return new DeliveryPackage(new Identifier("id_123"), products);
    }
}
