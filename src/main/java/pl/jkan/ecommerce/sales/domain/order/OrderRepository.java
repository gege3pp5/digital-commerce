package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface OrderRepository {

    public void add(Order order);

    public Order load(Identifier id);

    public Order loadByPayment(Identifier id);
}
