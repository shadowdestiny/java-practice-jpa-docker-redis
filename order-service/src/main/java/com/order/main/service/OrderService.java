package com.order.main.service;

import com.order.main.data.entity.OrderEntity;
import com.order.main.data.entity.ProductEntity;
import com.order.main.data.repository.OrderRepository;
import com.order.main.data.repository.ProductRepository;
import com.order.main.error.DataNotFoundException;
import com.order.main.error.InvalidSearchException;
import com.order.model.Order;
import com.order.model.OrderRequest;
import com.order.model.Product;
import com.order.model.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code ServiceService} service provides data access service for {@code Service}.
 * <p/>
 *
 * @author Luis Marin
 * @sice 28/04/19
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository repo;

    private Mapper mapper;

    @Autowired
    public OrderService(OrderRepository repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Order create(OrderRequest request) {

        OrderEntity entity = mapper.map(request, OrderEntity.class);
        entity = repo.save(entity);
        Order order = mapper.map(entity, Order.class);

        return order;
    }

    public List<Order> read() {
        return map(repo.findAll());
    }

    public Order read(Integer id) {
        OrderEntity entity = repo.findOne(id);
        return map(entity);
    }

    public void delete(Integer id) {
        repo.delete(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    private List<Order> map(List<OrderEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            throw new DataNotFoundException(
                    "No Orders found with the search criteria!");
        }

        return entities.stream().map(
                r -> mapper.map(r, Order.class)).collect(
                Collectors.toList());
    }

    private Order map(OrderEntity entity) {
        if (entity == null) {
            throw new InvalidSearchException("Order not found!");
        }

        return mapper.map(entity, Order.class);
    }
}
