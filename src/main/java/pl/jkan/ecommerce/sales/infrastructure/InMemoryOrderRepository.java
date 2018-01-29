package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;

import java.util.Optional;

public class InMemoryOrderRepository extends InMemoryRepository<Order> implements OrderRepository {
    @Override
    public Order loadByPayment(Identifier id) {

        Optional<Order> order = items.stream()
                .filter(o -> o.getPayment().getId().equals(id))
                .reduce((acc, first) -> first)
        ;

        if (!order.isPresent()) {
            throw new RuntimeException("Order not found");
        }

        return order.get();
    }
}
