package pl.jkan.przelewy24;

public class Przelewy24Properties {
    private String merchantId;
    private String crc;
    private String posId;
    private Boolean testEnv;

    public Przelewy24Properties(String merchantId, String posId, String crc) {
        this.merchantId = merchantId;
        this.crc = crc;
        this.posId = posId;
        this.testEnv = true;
    }

    public Przelewy24Properties(String merchantId, String posId, String crc, Boolean testEnv) {
        this.merchantId = merchantId;
        this.crc = crc;
        this.posId = posId;
        this.testEnv = testEnv;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getCrc() {
        return crc;
    }

    public String getPosId() {
        return posId;
    }

    public Boolean getTestEnv() {
        return testEnv;
    }
}
