package com.order.main.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * {@code OrderApplication} represents the entry point for order controller
 * spring order application.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/04/2019
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.order.main.config",
        "com.order.main.controller",
        "com.order.main.data.entity",
        "com.order.main.data.repository",
        "com.order.main.error",
        "com.order.model",
        "com.order.main.service"})
@EntityScan(basePackages = "com.order.main.data.entity")
@EnableJpaRepositories(basePackages = {"com.order.main.data.repository"})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}
