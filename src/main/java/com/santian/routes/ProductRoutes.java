package com.santian.routes;

import com.santian.repository.ProductRepository;
import com.santian.repository.model.Product;
import com.santian.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRoutes {

    @Autowired
    private ProductService service;

    @Bean
    public RouterFunction<ServerResponse> addProduct(){
        return route(POST("route/save/task"),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> service.addProduct(product))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> getProducts(){
        return route(GET("route/get/all"),
                request -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromPublisher(service.getTasks(), Product.class)));
    }

    public Mono<ToDo> updateTask(int id, String newTask){
        return toDoRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El registro no esta en la base de datos")))
                .flatMap(task -> toDoRepository.save(ToDo.from(newTask, id)));
    }

}
