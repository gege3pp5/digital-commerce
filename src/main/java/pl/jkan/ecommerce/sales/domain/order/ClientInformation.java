package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface ClientInformation {
    public ClientData getDataForClient(Identifier id);
}
