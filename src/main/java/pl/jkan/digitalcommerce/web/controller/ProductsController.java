package pl.jkan.digitalcommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.ProductDto;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.api.AddToBasketCommand;
import pl.jkan.ecommerce.sales.application.api.AddToBasketHandler;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductNotAvailableException;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.ProductFinder;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private AddToBasketHandler addToBasketHandler;

    @Autowired
    private ProductFinder productFinder;

    @RequestMapping("/products")
    @ResponseBody
    public List<ProductDto> products() {
        return productFinder.all();
    }

    @RequestMapping("/add-to-basket/{id}")
    public String addToBasket(@PathVariable(value="id") String id) {
        try {
            addToBasketHandler.handle(new AddToBasketCommand(new Identifier(id)));
            return "OK";
        } catch (ProductNotAvailableException e) {
            return "Something is not YES!!!!!";
        }
    }
}