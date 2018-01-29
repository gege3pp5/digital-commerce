package pl.jkan.ecommerce.sales.domain.payment;

import pl.jkan.ecommerce.sales.domain.payment.Payment;

public interface PaymentGateway {
    String obtainPaymentURL(Payment payment);

    String obtainPaymentToken(Payment payment);

    String obtainPaymentURL(String token);
}
