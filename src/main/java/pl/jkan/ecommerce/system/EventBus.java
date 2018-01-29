package pl.jkan.ecommerce.system;

public interface EventBus {
    public void dispatch(Object object);

    public void addEventListener(Class className, Subscriber subscriber);
}
