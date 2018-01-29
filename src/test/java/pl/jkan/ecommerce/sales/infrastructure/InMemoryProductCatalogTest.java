package pl.jkan.ecommerce.sales.infrastructure;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Entity;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;

public class InMemoryProductCatalogTest {

    @Test
    public void itAllowAddElementEntity() {
        InMemoryProductCatalog catalog = new InMemoryProductCatalog();

        catalog.add(exampleProduct("p_1"));
        catalog.add(exampleProduct("p_2"));

        Assert.assertNotNull(catalog.load(new Identifier("p_1")));
    }

    @Test
    public void itThrowWithExceptionWhenNoItem() {
        InMemoryProductCatalog catalog = new InMemoryProductCatalog();

        catalog.add(exampleProduct("p_1"));
        catalog.add(exampleProduct("p_2"));


        try {
            Assert.assertNotNull(catalog.load(new Identifier("p_3")));
            Assert.fail("Should throw not found exception");
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }

    private Product exampleProduct(String id) {
        return new Product(new Identifier(id));
    }

}


