package pl.jkan.ecommerce.canonicalmodel;

import org.junit.Assert;
import org.junit.Test;

public class IdentifierTest {
    @Test
    public void itCouldBeCompared() {
        Identifier i1 = new Identifier("p1");
        Identifier i2 = new Identifier("p2");
        Identifier i3 = new Identifier("p2");

        Assert.assertFalse(i1.equals(i2));
        Assert.assertTrue(i2.equals(i3));
    }

    @Test
    public void itRepresentIdInToStringMethod() {
        Identifier i1 = new Identifier("p1");

        Assert.assertEquals("p1", i1.toString());
    }
}
