package pl.jkan.ecommerce.system;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.system.infrastructure.InternalEventBus;

public class InternalEventBusTest {
    @Test
    public void itAllowHandleEvents() {
        EventBus b = new InternalEventBus();
        b.dispatch(new MyEvent());
    }

    @Test
    public void itAllowAddListenerThatHandleEvent() {
        EventBus b = new InternalEventBus();
        SpyMyEventListener listener = new SpyMyEventListener();

        b.addEventListener(MyEvent.class, listener);
        MyEvent e = new MyEvent();
        b.dispatch(e);

        Assert.assertEquals(e, listener.lastEvent);
    }

    class MyEvent {}

    class SpyMyEventListener implements Subscriber<MyEvent> {
        public MyEvent lastEvent;
        @Override
        public void handle(MyEvent event) {
            this.lastEvent = event;
        }
    }
}
