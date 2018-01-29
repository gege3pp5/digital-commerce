package pl.jkan.ecommerce.system;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class SystemUser {
    private Identifier id;

    public SystemUser(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }
}
