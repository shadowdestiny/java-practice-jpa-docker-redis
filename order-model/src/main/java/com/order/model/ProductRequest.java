package com.order.model;

import lombok.Data;

/**
 * {@code BookRequest} represents a request to create or update a book entity.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/04/19
 */
@Data
public class ProductRequest {

    private String name;
    private Float price;
    private String currency;
    private String sku;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
