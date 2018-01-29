package pl.jkan.ecommerce.sales.infrastructure;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Entity;
import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class InMemoryRepositoryTest {

    @Test
    public void itAllowAddElementEntity() {
        InMemoryRepository<Entity> repository = new InMemoryRepository<>();

        repository.add(exampleEntity("e_1"));
        repository.add(exampleEntity("e_2"));

        Assert.assertNotNull(repository.load(new Identifier("e_1")));
    }

    @Test
    public void itThrowWithExceptionWhenNoItem() {
        InMemoryRepository<Entity> repository = new InMemoryRepository<>();

        repository.add(exampleEntity("e_1"));
        repository.add(exampleEntity("e_2"));

        try {
            Assert.assertNotNull(repository.load(new Identifier("e_3")));
            Assert.fail("Should throw not found exception");
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }

    private Entity exampleEntity(String id) {
        return new ExampleEntity(new Identifier(id));
    }

    private class ExampleEntity implements Entity {

        private Identifier id;

        public ExampleEntity(Identifier id) {
            this.id = id;
        }

        @Override
        public Identifier getId() {
            return id;
        }
    }
}


