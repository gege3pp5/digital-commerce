package pl.jkan.ecommerce.delivery.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.OrderSpecifications;
import pl.jkan.ecommerce.delivery.domain.Specification;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderItem;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;

import java.util.ArrayList;

public class OrderRepositoryOrderSpecification implements OrderSpecifications {
    private OrderRepository repository;

    public OrderRepositoryOrderSpecification(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Specification get(Identifier orderId) {
        Order order = repository.load(orderId);

        ArrayList<Identifier> productsIds = order.getItems().stream()
                .map(OrderRepositoryOrderSpecification::unwindProducts)
                .reduce((x,y) -> {
                    y.addAll(x);
                    return y;
                })
                .get()
        ;
        return new Specification(
                orderId,
                productsIds,
                order.getClientData().getEmail()
        );
    }

    private static ArrayList<Identifier> unwindProducts(OrderItem item) {
        ArrayList<Identifier> items = new ArrayList<>();
        while (items.size() < item.getQuantity()) {
            items.add(item.getProductId());
        }

        return items;
    }
}
