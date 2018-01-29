package pl.jkan.ecommerce.delivery.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.preparation.KeySource;

import java.util.Random;

public class InfinitiveKeySource implements KeySource {
    @Override
    public String nextForProduct(Identifier id) {
        return String.format("%s-%s-%s-%s-%s", getSaltString(), getSaltString(), getSaltString(), getSaltString(),getSaltString());
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
