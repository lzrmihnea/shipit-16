package eu.accesa.shopit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CreatePurchaseRequest {

    private final String productName;
    private final Date date;

    public CreatePurchaseRequest(
            @JsonProperty(value = "productName", required = true) String productName,
            @JsonProperty(value = "date") Date date) {
        this.productName = productName;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public Date getDate() {
        return date;
    }
}
