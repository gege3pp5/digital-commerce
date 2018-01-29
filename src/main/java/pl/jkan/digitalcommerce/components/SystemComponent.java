package pl.jkan.digitalcommerce.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.delivery.application.OrderConfirmedListener;
import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.ecommerce.system.SystemUserContext;
import pl.jkan.ecommerce.system.infrastructure.InMemorySystemUserContext;
import pl.jkan.ecommerce.system.infrastructure.InternalEventBus;

@Component
public class SystemComponent {


    @Bean
    private SystemUserContext getSystemUserContext() {
        InMemorySystemUserContext systemUserContext = new InMemorySystemUserContext();
        systemUserContext.authenticate(new Identifier("user_1"));

        return systemUserContext;
    }

    @Bean
    private EventBus eventBus() {
        EventBus eventBus = new InternalEventBus();

        return eventBus;
    }
}


