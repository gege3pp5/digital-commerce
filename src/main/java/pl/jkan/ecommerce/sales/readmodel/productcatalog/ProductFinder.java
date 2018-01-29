package pl.jkan.ecommerce.sales.readmodel.productcatalog;

import java.util.ArrayList;
import java.util.List;

public class ProductFinder {
    public List<ProductDto> all() {
        List<ProductDto> productsList = new ArrayList<>();
        productsList.add(new ProductDto("p1", "Battlefield", "products/battlefield.png", 10.50, "No battle is ever the same"));
        productsList.add(new ProductDto("p2", "Diablo III", "products/diablo.png", 12.50, "The demonically-besieged world of Sanctuary needs heroes"));
        productsList.add(new ProductDto("p3", "Starcraft II", "products/starcraft.png", 13.50, "Wage war across the galaxy with three unique and powerful races"));

        return productsList;
    };
}
