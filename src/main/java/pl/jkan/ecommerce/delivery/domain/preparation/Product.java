package pl.jkan.ecommerce.delivery.domain.preparation;

public class Product {
    private String name;
    private String key;

    public Product(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
