package pl.jkan.ecommerce.sales.domain.productcatalog;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface ProductCatalog {
    public Product load(Identifier id);

    public void add(Product product);
}
