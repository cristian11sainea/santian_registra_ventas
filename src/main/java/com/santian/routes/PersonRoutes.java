package com.santian.routes;

import com.santian.repository.model.Person;
import com.santian.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PersonRoutes {

    @Autowired
    private PersonService service;

    @Bean
    public RouterFunction<ServerResponse> addPerson(){
        return route(POST("route/save/person"),
                request -> request.bodyToMono(Person.class)
                        .flatMap(person -> service.addPerson(person))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> updatePerson(){
        return route(PUT("route/update/person"),
                request -> request.bodyToMono(Person.class)
                        .flatMap(person -> service.updatePerson(person))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> deletePerson(){
        return route(DELETE("route/delete/person"),
                request -> request.bodyToMono(Person.class)
                        .flatMap(id -> service.deletePerson(id))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }

    @Bean
    public RouterFunction<ServerResponse> getPeople() {
        return route(GET("route/get/people"),
                request -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromPublisher(service.getPeople(), Person.class)));
    }

}