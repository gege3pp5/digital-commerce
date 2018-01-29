package pl.jkan.ecommerce.system.infrastructure;

import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.ecommerce.system.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternalEventBus implements EventBus {

    private Map<Class, List> subscribers = new HashMap<>();

    @Override
    public void dispatch(Object object) {
        if (!subscribers.containsKey(object.getClass())) {
            return;
        }

        List<Subscriber> eventSubscribers = subscribers.get(object.getClass());

        eventSubscribers
                .stream()
                .forEach(x -> x.handle(object))
        ;
    }

    @Override
    public void addEventListener(Class className, Subscriber subscriber) {
        if (!subscribers.containsKey(className)) {
            subscribers.put(className, new ArrayList());
        }

        List<Subscriber> classSubscribers = subscribers.get(className);
        classSubscribers.add(subscriber);
    }
}
