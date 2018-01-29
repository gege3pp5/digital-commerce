package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.order.ClientData;
import pl.jkan.ecommerce.sales.domain.order.ClientInformation;

public class InMemoryClientInformation implements ClientInformation {
    @Override
    public ClientData getDataForClient(Identifier id) {
        return new ClientData(id, "some.email@dev.dev");
    }
}
