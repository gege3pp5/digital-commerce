package pl.jkan.ecommerce.sales.domain.payment;

import pl.jkan.ecommerce.sales.domain.payment.exception.PaymentVerificationError;

public interface PaymentConfirmation {
    public void verifyPayment(Payment payment, String checksum, String orderId) throws PaymentVerificationError;
}
