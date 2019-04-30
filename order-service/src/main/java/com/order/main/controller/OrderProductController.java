package com.order.main.controller;

import com.order.main.service.OrderProductService;
import com.order.main.service.OrderService;
import com.order.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@code OrderProductController} is the spring REST controller for OrderProduct API. Exposes
 * all CRUD operations on OrderProduct.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/4/2019
 */
@RestController
@Slf4j
@Api(value = "OrderProduct API",
        description = "OrderProduct API",
        produces = "application/json", tags = {"API"})
public class OrderProductController {

    public static final String OrderProduct_URL = "/OrderProducts";

    public static final String OrderProduct_BY_ID_URL = OrderProduct_URL + "/{order_id}/{product_id}";
    public static final String OrderProduct_BY_ID_URL_ONE = OrderProduct_URL + "/{order_id}";


    @Autowired
    private OrderProductService service;

    @ApiOperation(
            value = "Creates an OrderProduct.",
            notes = "Requires OrderProduct title and name of the author.",
            response = OrderProduct.class)
    @ApiResponses({
            @ApiResponse(code = 201, response = Override.class,
                    message = "Override override created successfully")})
    @RequestMapping(method = RequestMethod.POST, value = OrderProduct_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderProduct create(@RequestBody OrderProductRequest request) {
        return service.create(request);
    }

    @ApiOperation(
            value = "Retrieves all OrderProducts associated with a title, author, category or combination of them.",
            response = OrderProduct.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, value = OrderProduct_BY_ID_URL_ONE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<OrderProduct> read(
            @PathVariable("order_id") Integer orderId
    ){
            return service.read(orderId);
    }

    @ApiOperation(value = "Updates a Product.", response = OrderProduct.class)
    @ApiResponses({
            @ApiResponse(code = 201, response = OrderProduct.class,
                    message = "Updated a Product created successfully")})
    @RequestMapping(method = RequestMethod.PUT, value = OrderProduct_BY_ID_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderProduct update(
            @PathVariable("order_id") Integer orderId,
            @PathVariable("product_id") Integer productId,
            @RequestBody OrderProductOnUpdateRequest request) {
        return service.update(productId,orderId, request);
    }

    @ApiOperation(value = "Deletes a OrderProduct by ID.")
    @RequestMapping(method = RequestMethod.DELETE, value = OrderProduct_BY_ID_URL)
    @ResponseBody
    public void deleteById(
            @PathVariable("order_id") Integer orderId,
            @PathVariable("product_id") Integer productId
            ) {
        service.delete(orderId,productId);
    }

}
