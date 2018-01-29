package pl.jkan.ecommerce.sales.domain.payment;

public class NullPaymentGateway implements PaymentGateway{
    @Override
    public String obtainPaymentURL(Payment payment) {
        return null;
    }

    @Override
    public String obtainPaymentToken(Payment payment) {
        return null;
    }

    @Override
    public String obtainPaymentURL(String token) {
        return null;
    }
}
