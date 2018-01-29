package pl.jkan.ecommerce.sales.application.services.payment;

import pl.jkan.ecommerce.sales.domain.payment.PaymentGateway;
import pl.jkan.przelewy24.Model.ApiResponse;
import pl.jkan.przelewy24.Model.RegisterPaymentData;
import pl.jkan.przelewy24.Przelewy24Api;
import pl.jkan.ecommerce.sales.domain.payment.Payment;

public class Przelewy24PaymentGateway implements PaymentGateway {
    private Przelewy24Api api;
    private String returnUrl;
    private String statusUrl;

    public Przelewy24PaymentGateway(Przelewy24Api api, String returnUrl, String statusUrl) {
        this.api = api;
        this.returnUrl = returnUrl;
        this.statusUrl = statusUrl;
    }

    @Override
    public String obtainPaymentURL(Payment payment) {

        ApiResponse response = api.registerPayment(new RegisterPaymentData(
                payment.getId().toString().concat(""),
                payment.expressValueInSmallestUnit(),
                payment.getClientData().getEmail(),
                returnUrl,
                "Zakup w sklepie UEK",
                statusUrl
        ));

        return api.getPaymentUrl(response.getValue("token"));
    }

    @Override
    public String obtainPaymentToken(Payment payment) {

        ApiResponse response = api.registerPayment(new RegisterPaymentData(
                payment.getId().toString().concat(""),
                payment.expressValueInSmallestUnit(),
                payment.getClientData().getEmail(),
                returnUrl,
                "Zakup w sklepie UEK",
                statusUrl
        ));

        return response.getValue("token");
    }

    @Override
    public String obtainPaymentURL(String token) {

        return api.getPaymentUrl(token);
    }
}
