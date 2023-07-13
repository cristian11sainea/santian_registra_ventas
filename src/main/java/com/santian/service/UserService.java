package com.santian.service;

import com.santian.repository.UserRepository;
import com.santian.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserService {

    @Autowired
    UserRepository repository;

    public Mono<User> addUser (String name, String typeUser){
        return repository.save(new User(name,typeUser));
    }

    public Flux<User> getUsers(){
        return repository.findAll();
    }

    public Mono<User> updateUser(String id, String name, String typeUser){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El usuario no ha sido creada")))
                .flatMap(user -> repository.save(User.updateUser(id, name, typeUser)));
    }

    public Mono<Void> deleteUser(String id){
        return repository.deleteById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El usuario no ha sido creada")));
    }

}
