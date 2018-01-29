package pl.jkan.ecommerce.system.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.system.SystemUser;
import pl.jkan.ecommerce.system.SystemUserContext;

public class InMemorySystemUserContext implements SystemUserContext {

    private SystemUser systemUser;

    public void authenticate(Identifier id) {
        this.systemUser = new SystemUser(id);
    }

    @Override
    public SystemUser getCurrentUser() {
        return systemUser;
    }
}
