package com.santian.service;

import com.santian.repository.PersonRepository;
import com.santian.repository.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonService {

    @Autowired
    PersonRepository repository;

    public Mono<Person> addPerson(String name, String lastName, Long document, String typePerson){
        return repository.save(new Person(name, lastName, document, typePerson));
    }

    public Flux<Person> getPeople(){
        return repository.findAll();
    }

    public Mono<Person> updatePerson(String id, String name, String lastName, Long document, String typePerson){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La persona no ha sido creada")))
                .flatMap(person -> repository.save(Person.updatePerson(id, name, lastName, document, typePerson)));
    }

    public Mono<Void> deletePerson(String id){
        return repository.deleteById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La persona no ha sido creada")));

    }

}
