package com.order.main.service;

import com.order.main.data.entity.OrderEntity;
import com.order.main.data.entity.OrderProductEntity;
import com.order.main.data.entity.ProductEntity;
import com.order.main.data.repository.OrderProductRepository;
import com.order.main.data.repository.OrderRepository;
import com.order.main.data.repository.ProductRepository;
import com.order.main.error.DataAlreadyRegisteredException;
import com.order.main.error.DataNotFoundException;
import com.order.main.error.InvalidSearchException;
import com.order.model.*;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import redis.clients.jedis.Jedis;


/**
 * {@code OrderProductService} service provides data access service for {@code OrderProductService}.
 * <p/>
 *
 * @author Luis Marin
 * @sice 28/04/19
 */
@Service
@Slf4j
public class OrderProductService {

    @Autowired
    private OrderProductRepository repo;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Mapper mapper;

    @Autowired
    public OrderProductService(OrderProductRepository repo, ProductRepository productRepository, OrderRepository orderRepository, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    private String getProperty(String property){
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(property);
    }

    private Float getCurrencyValueTransformated(String fromCurrency, Float value){

        Jedis client = new Jedis(this.getProperty("URL_REDIS"),Integer.parseInt(this.getProperty("URL_PORT")));
        String currencyPersister = client.get("currency");
        String toCurrency = this.getProperty("DEFAULT_CURRENCY");
        String baseCurrency = this.getProperty("BASE_CURRENCY");

        if (currencyPersister == null){
            String key = this.getProperty("FIXER_KEY");

            RestTemplate template = new RestTemplate();
            String result = template.getForObject("http://data.fixer.io/api/latest?access_key="+key, String.class);

            client.set("currency",result);
            client.expire("currency", 3600);

            currencyPersister = result;
        }

        JSONParser parser = new JSONParser();

        Double fromCurrencyValue = 0.0;
        Double toCurrencyValue = 0.0;
        Double baseCurrencyValue = 0.0;

        try {
            Object obj = parser.parse(currencyPersister);
            JSONObject object = (JSONObject)obj;
            JSONObject rates = (JSONObject) object.get("rates");

            fromCurrencyValue = Double.valueOf(rates.get(fromCurrency).toString());
            toCurrencyValue = Double.valueOf(rates.get(toCurrency).toString());
            baseCurrencyValue = Double.valueOf(rates.get(baseCurrency).toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (float)(((fromCurrencyValue * baseCurrencyValue) / toCurrencyValue ) * value);
    }

    private Product map(ProductEntity entity) {
        if (entity == null) {
            throw new InvalidSearchException("Product not found!");
        }

        return mapper.map(entity, Product.class);
    }

    @Transactional
    public OrderProduct create(OrderProductRequest request) {

        if (repo.findOneByProductIdAndOrderId(request.getProductId(),request.getOrderId()) != null){
            throw new DataAlreadyRegisteredException(
                    "OrderProducts already registered!");
        }

        OrderProduct orderProduct = mapper.map(request, OrderProduct.class);
        Product product = map(productRepository.findOne(request.getProductId()));
        orderProduct.setProduct(product);

        OrderProductEntity orderProductEntity = repo.save(mapper.map(orderProduct,OrderProductEntity.class));
        orderProduct = mapper.map(orderProductEntity,OrderProduct.class);

        List<OrderProduct> orderProducts = map(repo.findByOrderId(request.getOrderId()));

        Float value = 0f;
        for (OrderProduct op: orderProducts) {
            value = value + getCurrencyValueTransformated(op.getProduct().getCurrency(),op.getProduct().getPrice());
        }

        Order order = mapper.map(orderRepository.findOne(request.getOrderId()),Order.class);
        order.setTotal(value);
        order.setCurrency(this.getProperty("DEFAULT_CURRENCY"));
        orderRepository.save(mapper.map(order, OrderEntity.class));

        return orderProduct;
    }

    public List<OrderProduct> read(Integer orderId) {
        return map(repo.findByOrderId(orderId));
    }

    private List<OrderProduct> map(List<OrderProductEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            throw new DataNotFoundException(
                    "No OrderProducts found with the search criteria!");
        }

        return entities.stream().map(
                r -> mapper.map(r, OrderProduct.class)).collect(
                Collectors.toList());
    }

    private OrderProduct map(OrderProductEntity entity) {
        if (entity == null) {
            throw new InvalidSearchException("OrderProduct not found!");
        }

        return mapper.map(entity, OrderProduct.class);
    }

    public void delete(Integer orderId, Integer productId) {
        OrderProductEntity productEntity = repo.findOneByProductIdAndOrderId(productId,orderId);
        repo.delete(productEntity);
    }

    @Transactional
    public OrderProduct update(Integer productId, Integer OrderId, OrderProductOnUpdateRequest request) {
        OrderProductEntity entity = repo.findOneByProductIdAndOrderId(productId,OrderId);

        if (entity == null) {
            throw new DataNotFoundException(
                    "OrderProduct with productId " + productId + " and "+OrderId+" not found!");
        }

        validate(request);
        mapper.map(request, entity);
        entity = repo.save(entity);

        OrderProduct orderProduct = mapper.map(entity, OrderProduct.class);

        return orderProduct;
    }

    private void validate(OrderProductOnUpdateRequest request) {
        Assert.notNull(request.getProductId(), "ProductId can't be null!");
    }
}
