package com.order.main.controller;

import com.order.main.service.OrderProductService;
import com.order.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * {@code OrderControllerUnitTests} unit test class for {@code OrderController}.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/04/19
 */
public class OrderProductControllerUnitTests {

    @Mock
    private OrderProductService service;

    @InjectMocks
    private OrderProductController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        OrderProduct orderProduct = getOrderProduct();
        when(service.create(any(OrderProductRequest.class))).thenReturn(orderProduct);

        OrderProduct returnedObj = controller.create(new OrderProductRequest());
        validate(orderProduct, returnedObj);
    }

    @Test
    public void testReadById() {
        OrderProduct orderProduct = getOrderProduct();
        List<OrderProduct> orderProducts = new ArrayList<>();
        orderProducts.add(orderProduct);
        when(service.read(any(Integer.class))).thenReturn(orderProducts);

        List<OrderProduct> returnedObjs = controller.read(orderProduct.getId());
        assertEquals(1, returnedObjs.size());
        validate(orderProduct, returnedObjs.get(0));
    }


    @Test
    public void testDeleteById() {
        doNothing().when(service).delete(any(Integer.class),any(Integer.class));

        controller.deleteById(1,1);
    }

    private void validate(OrderProduct expected, OrderProduct actual) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getOrderId(), actual.getOrderId());
    }

    private OrderProduct getOrderProduct() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(1);

        Product product = new Product();
        product.setId(1);
        product.setName("Producto1");
        product.setPrice(100f);
        product.setCurrency("USD");

        orderProduct.setProduct(product);


        return orderProduct;
    }
}
