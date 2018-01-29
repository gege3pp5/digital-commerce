package pl.jkan.przelewy24.Model;

public class VerifyPaymentData {
    private String sessionId;
    private String orderId;
    private Integer amount;

    private String currency = "PLN";

    public VerifyPaymentData(String sessionId, String orderId, Integer amount) {
        this.sessionId = sessionId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
