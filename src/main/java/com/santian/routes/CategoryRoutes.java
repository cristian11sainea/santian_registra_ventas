package com.santian.routes;

import com.santian.repository.model.Category;
import com.santian.repository.model.Product;
import com.santian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CategoryRoutes {

    @Autowired
    private CategoryService service;

    @Autowired
    private Category category;

    @Bean
    public RouterFunction<ServerResponse> addCategory(){
        return route(POST("route/save/category"),
                request -> request.bodyToMono(Category.class)
                        .flatMap(category -> service.addCategory(category))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> updateCategory(){
        return route(PUT("route/update/category"),
                request -> request.bodyToMono(Category.class)
                        .flatMap(category -> service.updateCategory(category))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteCategory(){
        return route(DELETE("route/delete/category"),
                request -> request.bodyToMono(Category.class)
                        .flatMap(id -> service.deleteCategory(id))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> getCategories() {
        return route(GET("route/get/allcategories"),
                request -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromPublisher(service.getCategories(), Category.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getProductsInCategory(){
        return route(GET("route/get/products/{categoryName}"),
                request -> ServerResponse
                       .ok()
                        .body(BodyInserters.fromPublisher(service.productsInCategory(request.pathVariable("category")), Product.class)));
    }


}