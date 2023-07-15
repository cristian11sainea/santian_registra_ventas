package com.santian.routes;

import com.santian.repository.model.User;
import com.santian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouters {

    @Autowired
    private UserService service;

    @Bean
    public RouterFunction<ServerResponse> addUser(){
        return route(POST("route/save/user"),
                request -> request.bodyToMono(User.class)
                        .flatMap(user -> service.addUser(user))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> updateUser(){
        return route(PUT("route/update/user"),
                request -> request.bodyToMono(User.class)
                        .flatMap(user-> service.updateUser(user))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteUser(){
        return route(DELETE("route/delete/user"),
                request -> request.bodyToMono(User.class)
                        .flatMap(id -> service.deleteUser(id))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> getUsers() {
        return route(GET("route/get/users"),
                request -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromPublisher(service.getUsers(), User.class)));
    }

}