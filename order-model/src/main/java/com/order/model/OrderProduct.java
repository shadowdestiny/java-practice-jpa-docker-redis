package com.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code OrderProduct} represents a book entity.
 * <p/>
 *
 * @author lmarin
 * @since 28/4/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    private Integer id;
    private Integer orderId;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
