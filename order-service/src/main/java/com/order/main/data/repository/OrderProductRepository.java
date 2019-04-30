package com.order.main.data.repository;

import com.order.main.data.entity.OrderProductEntity;
import com.order.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * {@code OrderRepository} exposes all CRUD operations on a data of type
 * {@code Order}.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/4/19
 */
public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Integer> {


    List<OrderProductEntity> findByOrderId(Integer orderId);
    OrderProductEntity findOneByProductIdAndOrderId(Integer productId, Integer orderId);

}
