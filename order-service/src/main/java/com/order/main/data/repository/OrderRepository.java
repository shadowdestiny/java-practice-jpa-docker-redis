package com.order.main.data.repository;

import com.order.main.data.entity.OrderEntity;
import com.order.main.data.entity.ProductEntity;
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
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    OrderEntity findOne(Integer id);
    List<OrderEntity> findAll();

}
