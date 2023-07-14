package com.santian.routes;

import com.santian.repository.model.Product;
import com.santian.repository.model.Report;
import com.santian.routes.exception.ExceptionHandler;
import com.santian.service.ProductService;
import com.santian.usescases.RegisterSaleOrPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRoutes {

    @Autowired
    private ProductService service;
    @Autowired
    private ExceptionHandler handler;

    @Bean
    public RouterFunction<ServerResponse> addProduct(){
        return route(POST("route/save/product"),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> service.addProduct(product))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> updateProduct(){
        return route(POST("route/update/product"),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> service.updateProduct(product))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteProduct(){
        return route(DELETE("route/delete/product"),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> service.deleteTask(product))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> getProducts(){
        return route(GET("route/get/allproducts"),
                request -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromPublisher(service.getTasks(), Product.class)));
    }



}
