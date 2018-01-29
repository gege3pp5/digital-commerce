package pl.jkan.ecommerce.canonicalmodel;

import java.util.UUID;

public class Identifier {

    private String id;

    public static Identifier byString(String id) {
        return new Identifier(id);
    }

    public static Identifier generateUUID() {
        return new Identifier(UUID.randomUUID().toString());
    }

    public Identifier(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Identifier other = (Identifier) obj;

        return other.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
