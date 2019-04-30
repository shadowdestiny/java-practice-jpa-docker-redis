package com.order.model;

import lombok.Data;

/**
 * {@code OrderRequest} represents a request to create or update a book entity.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/4/19
 */
@Data
public class OrderProductRequest {

    private Integer productId;
    private Integer orderId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
