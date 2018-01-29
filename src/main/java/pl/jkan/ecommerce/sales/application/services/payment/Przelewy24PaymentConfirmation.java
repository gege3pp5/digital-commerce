package pl.jkan.ecommerce.sales.application.services.payment;

import pl.jkan.ecommerce.sales.domain.payment.Payment;
import pl.jkan.ecommerce.sales.domain.payment.PaymentConfirmation;
import pl.jkan.ecommerce.sales.domain.payment.exception.PaymentVerificationError;
import pl.jkan.przelewy24.InvalidRequestException;
import pl.jkan.przelewy24.Model.VerifyPaymentData;
import pl.jkan.przelewy24.Przelewy24Api;

public class Przelewy24PaymentConfirmation implements PaymentConfirmation {
    private Przelewy24Api api;

    public Przelewy24PaymentConfirmation(Przelewy24Api api) {
        this.api = api;
    }

    public void verifyPayment(Payment payment, String checksum, String orderId) throws PaymentVerificationError {

        try {
            api.verifyPayment(new VerifyPaymentData(
                    payment.getId().toString(),
                    orderId,
                    payment.expressValueInSmallestUnit()
            ));
        } catch (InvalidRequestException e) {
            throw new PaymentVerificationError();
        }
    }
}
