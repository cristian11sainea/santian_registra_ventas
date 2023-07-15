package com.santian.service;

import com.santian.repository.PersonRepository;
import com.santian.repository.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public Mono<Person> addPerson(Person person){
        return repository.save(new Person(person.name, person.lastName, person.document, person.typePerson));
    }

    public Flux<Person> getPeople(){
        return repository.findAll();
    }

    public Mono<Person> updatePerson(Person newPerson){
        return repository.findById(newPerson.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La persona no ha sido creada")))
                .flatMap(person -> repository.save(Person.updatePerson(newPerson.id, newPerson.name, newPerson.lastName, newPerson.document, newPerson.typePerson)));
    }

    public Mono<Void> deletePerson(Person person){
        return repository.findById(person.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La persona no ha sido creada")))
                .flatMap(delPerson -> repository.deleteById(delPerson.id));
    }

}
