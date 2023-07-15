package com.santian.service;

import com.santian.repository.UserRepository;
import com.santian.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public Mono<User> addUser (User user){
        return repository.save(new User(user.name, user.typeUser));
    }

    public Flux<User> getUsers(){
        return repository.findAll();
    }

    public Mono<User> updateUser(User newUser){
        return repository.findById(newUser.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El usuario no ha sido creada")))
                .flatMap(user -> repository.save(User.updateUser(newUser.id, newUser.name, newUser.typeUser)));
    }

    public Mono<Void> deleteUser(User user) {
        return repository.findById(user.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El usuario no ha sido creado")))
                .flatMap(delUser -> repository.deleteById(delUser.id));
    }

}