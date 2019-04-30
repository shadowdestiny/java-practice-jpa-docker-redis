package com.order.main.controller;

import com.order.main.service.ProductService;
import com.order.model.Product;
import com.order.model.ProductRequest;
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
public class ProductUnitTests {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Product product = getProduct();
        when(service.create(any(ProductRequest.class))).thenReturn(product);

        Product returnedObj = controller.create(new ProductRequest());
        validate(product, returnedObj);
    }

    @Test
    public void testReadById() {
        Product product = getProduct();
        when(service.read(any(Integer.class))).thenReturn(product);

        Product returnedObj = controller.read(product.getId());
        validate(product, returnedObj);
    }

    @Test
    public void testRead() {
        Product product = getProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(service.read(any(String.class))).thenReturn(products);

        List<Product> returnedObjs =
                 controller.read("Producto1");
        assertEquals(1, returnedObjs.size());
        validate(product, returnedObjs.get(0));
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

    private void validate(Product expected, Product actual) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getName(), actual.getName());
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setCurrency("USD");
        product.setName("Producto1");
        product.setPrice(100f);

        return product;
    }
}
