package com.order.main.data.repository;

import com.order.main.data.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * {@code ProductRepository} exposes all CRUD operations on a data of type
 * {@code Product}.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/04/19
 */
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    List<ProductEntity> findByNameIsLike(String name);
    List<ProductEntity> findAll();
    ProductEntity findOne(Integer id);

}
