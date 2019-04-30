package com.order.main.controller;

import com.order.main.service.ProductService;
import com.order.model.Product;
import com.order.model.ProductRequest;
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
 * {@code ProductController} is the spring REST controller for Product API. Exposes
 * all CRUD operations on Product.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/4/2019
 */
@RestController
@Slf4j
@Api(value = "Product API",
        description = "Product API",
        produces = "application/json", tags = {"API"})
public class ProductController {

    public static final String Product_URL = "/products";

    public static final String Product_BY_ID_URL = Product_URL + "/{id}";


    @Autowired
    private ProductService service;

    @ApiOperation(
            value = "Creates an Product.",
            notes = "Requires Product title and name of the author.",
            response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 201, response = Override.class,
                    message = "Override override created successfully")})
    @RequestMapping(method = RequestMethod.POST, value = Product_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody ProductRequest request) {
        return service.create(request);
    }

    @ApiOperation(
            value = "Retrieves a Product by ID.",
            notes = "Requires a Product identifier",
            response = Product.class)
    @RequestMapping(method = RequestMethod.GET, value = Product_BY_ID_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product read(@PathVariable("id") Integer id) {
        return service.read(id);
    }

    @ApiOperation(
            value = "Retrieves all Products associated with a title, author, category or combination of them.",
            response = Product.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, value = Product_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> read(
            @RequestParam(value = "name", required = false) String name
    ){
        if (name == null)
            return service.read();
        else
            return service.read(name);
    }

    @ApiOperation(value = "Updates a Product.", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 201, response = Product.class,
                    message = "Updated a Product created successfully")})
    @RequestMapping(method = RequestMethod.PUT, value = Product_BY_ID_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable("id") Integer id,
            @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @ApiOperation(value = "Deletes a Product by ID.")
    @RequestMapping(method = RequestMethod.DELETE, value = Product_BY_ID_URL)
    @ResponseBody
    public void deleteById(@PathVariable("id") Integer id) {
        service.delete(id);
    }

    @ApiOperation(value = "Deletes all Products.")
    @RequestMapping(method = RequestMethod.DELETE, value = Product_URL)
    @ResponseBody
    public void deleteAll() {
        service.deleteAll();
    }
}
