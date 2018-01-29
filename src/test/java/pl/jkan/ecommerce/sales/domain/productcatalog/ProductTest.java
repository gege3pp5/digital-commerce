package pl.jkan.ecommerce.sales.domain.productcatalog;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;

public class ProductTest {

    @Test
    public void itVerifyStockAvailability() {
        Product inStock = new Product(Identifier.byString("lego"), 10);
        Product notInStock = new Product(Identifier.byString("lego"), 0);

        Assert.assertTrue(inStock.isInStock());
        Assert.assertFalse(notInStock.isInStock());
    }
}
