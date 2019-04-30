package com.order.main.controller;

import com.order.main.service.OrderService;
import com.order.model.Order;
import com.order.model.OrderRequest;
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
public class OrderControllerUnitTests {

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Order order = getOrder();
        when(service.create(any(OrderRequest.class))).thenReturn(order);

        Order returnedObj = controller.create(new OrderRequest());
        validate(order, returnedObj);
    }

    @Test
    public void testReadById() {
        Order order = getOrder();
        when(service.read(any(Integer.class))).thenReturn(order);

        Order returnedObj = controller.read(order.getId());
        validate(order, returnedObj);
    }

    @Test
    public void testRead() {
        Order order = getOrder();
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(service.read()).thenReturn(orders);

        List<Order> returnedObjs =
                 controller.read();
        assertEquals(1, returnedObjs.size());
        validate(order, returnedObjs.get(0));
    }

    @Test
    public void testDeleteById() {
        doNothing().when(service).delete(any(Integer.class));

        controller.deleteById(1);
    }

    @Test
    public void testDeleteAll() {
        doNothing().when(service).deleteAll();

        controller.deleteAll();
    }

    private void validate(Order expected, Order actual) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getTotal(), actual.getTotal());
    }

    private Order getOrder() {
        Order order = new Order();
        order.setId(1);
        order.setCurrency("USD");
        order.setTotal(100.0f);

        return order;
    }
}
