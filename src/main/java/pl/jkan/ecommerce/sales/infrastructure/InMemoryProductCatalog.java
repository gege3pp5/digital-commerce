package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductCatalog implements ProductCatalog {
    private List<Product> products;

    public InMemoryProductCatalog() {
        this.products = new ArrayList<>();
    }

    @Override
    public Product load(Identifier id) {
        Optional<Product> prod = products.stream()
                .filter((product -> product.getId().equals(id)))
                .reduce((acc, item) -> item)
        ;

        if (!prod.isPresent()) {
            throw new RuntimeException("Product not found");
        }

        return prod.get();
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }
}
