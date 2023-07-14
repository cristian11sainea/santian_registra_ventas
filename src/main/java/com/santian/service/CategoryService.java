package com.santian.service;

import com.santian.repository.CategoryRepository;
import com.santian.repository.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public Mono<Category> addCategory(String name){
        return repository.save(new Category(name));
    }

    public Flux<Category> getCategories(){
        return repository.findAll();
    }

    public Mono<Category> updateCategory(String id, String newName){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La categoria no ha sido creada")))
                .flatMap(category -> repository.save(Category.updateCategory(id, newName)));
    }

    public Mono<Void> deleteCategory(String id){
        return repository.deleteById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La categoria no ha sido creada")));
    }

}
