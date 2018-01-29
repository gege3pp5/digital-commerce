package pl.jkan.ecommerce.system;

public interface Subscriber<T> {
    public void handle(T event);
}
