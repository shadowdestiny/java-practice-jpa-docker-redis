package com.order.main.controller;

import com.order.main.service.OrderService;
import com.order.model.Order;
import com.order.model.OrderRequest;
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
 * {@code OrderController} is the spring REST controller for Order API. Exposes
 * all CRUD operations on Order.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/4/2019
 */
@RestController
@Slf4j
@Api(value = "Order API",
        description = "Order API",
        produces = "application/json", tags = {"API"})
public class OrderController {

    public static final String Order_URL = "/orders";

    public static final String Order_BY_ID_URL = Order_URL + "/{id}";


    @Autowired
    private OrderService service;

    @ApiOperation(
            value = "Creates an Order.",
            notes = "Requires Order title and name of the author.",
            response = Order.class)
    @ApiResponses({
            @ApiResponse(code = 201, response = Override.class,
                    message = "Override override created successfully")})
    @RequestMapping(method = RequestMethod.POST, value = Order_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody OrderRequest request) {
        return service.create(request);
    }

    @ApiOperation(
            value = "Retrieves a Order by ID.",
            notes = "Requires a Order identifier",
            response = Order.class)
    @RequestMapping(method = RequestMethod.GET, value = Order_BY_ID_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Order read(@PathVariable("id") Integer id) {
        return service.read(id);
    }

    @ApiOperation(
            value = "Retrieves all Orders associated with a title, author, category or combination of them.",
            response = Order.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, value = Order_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Order> read(

    ){
            return service.read();
    }

    @ApiOperation(value = "Deletes a Order by ID.")
    @RequestMapping(method = RequestMethod.DELETE, value = Order_BY_ID_URL)
    @ResponseBody
    public void deleteById(@PathVariable("id") Integer id) {
        service.delete(id);
    }

    @ApiOperation(value = "Deletes all Orders.")
    @RequestMapping(method = RequestMethod.DELETE, value = Order_URL)
    @ResponseBody
    public void deleteAll() {
        service.deleteAll();
    }
}
