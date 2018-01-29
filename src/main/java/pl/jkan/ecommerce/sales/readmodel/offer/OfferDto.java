package pl.jkan.ecommerce.sales.readmodel.offer;

public class OfferDto {
    private Double total;
    private Integer itemsCount;
    private String currency = "PLN";

    public OfferDto(Double total, Integer itemsCount) {
        this.total = total;
        this.itemsCount = itemsCount;
    }

    public Double getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public String getCurrency() {
        return currency;
    }
}
