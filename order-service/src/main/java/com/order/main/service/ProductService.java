package com.order.main.service;

import com.order.main.data.entity.ProductEntity;
import com.order.main.data.repository.ProductRepository;
import com.order.main.error.DataNotFoundException;
import com.order.main.error.InvalidSearchException;
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
 * {@code ProductService} service provides data access service for {@code Product}.
 * <p/>
 *
 * @author Luis Marin
 * @sice 28/04/19
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repo;

    private Mapper mapper;

    @Autowired
    public ProductService(ProductRepository repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Product create(ProductRequest request) {
        validate(request);

        ProductEntity entity = mapper.map(request, ProductEntity.class);
        entity = repo.save(entity);
        Product product = mapper.map(entity, Product.class);

        return product;
    }

    public Product read(Integer id) {
        ProductEntity entity = repo.findOne(id);
        return map(entity);
    }

    public List<Product> read(String name) {
        if (name != null) {
            return map(repo.findByNameIsLike(name.trim()));
        } else {
            throw new InvalidSearchException(
                    "Specify at least one search critera!");
        }
    }

    public List<Product> read() {

        return map(repo.findAll());

    }

    @Transactional
    public Product update(Integer id, ProductRequest request) {
        ProductEntity entity = repo.findOne(id);

        if (entity == null) {
            throw new DataNotFoundException(
                    "Product with id " + id + " not found!");
        }

        validate(request);
        mapper.map(request, entity);
        entity = repo.save(entity);

        Product product = mapper.map(entity, Product.class);

        return product;
    }

    public void delete(Integer id) {
        repo.delete(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    private List<Product> map(List<ProductEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            throw new DataNotFoundException(
                    "No Products found with the search criteria!");
        }

        return entities.stream().map(
                r -> mapper.map(r, Product.class)).collect(
                Collectors.toList());
    }

    private void validate(ProductRequest request) {
        Assert.notNull(request.getName(), "Name can't be null!");
        Assert.notNull(request.getPrice(), "Price can't be null!");
        Assert.notNull(request.getCurrency(), "Currency can't be null!");
    }

    private Product map(ProductEntity entity) {
        if (entity == null) {
            throw new InvalidSearchException("Product not found!");
        }

        return mapper.map(entity, Product.class);
    }
}
