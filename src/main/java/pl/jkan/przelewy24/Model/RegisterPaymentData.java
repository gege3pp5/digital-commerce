package pl.jkan.przelewy24.Model;

public class RegisterPaymentData {
    private String sessionId;
    private Integer amount;
    private String email;
    private String urlReturn;
    private String statusUrl;
    private String description = "";
    private String currency = "PLN";
    private String country = "PL";
    private String apiVersion = "3.2";

    public RegisterPaymentData(String sessionId, Integer amount, String email, String urlReturn, String description, String statusUrl) {
        this.sessionId = sessionId;
        this.amount = amount;
        this.email = email;
        this.urlReturn = urlReturn;
        this.description = description;
        this.statusUrl = statusUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlReturn() {
        return urlReturn;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCountry() {
        return country;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getStatusUrl() {
        return statusUrl;
    }
}
