package com.santian.service;

import com.santian.repository.CategoryRepository;
import com.santian.repository.ProductRepository;
import com.santian.repository.model.Category;
import com.santian.repository.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private Category category;

    public Mono<Category> addCategory(Category category){
        return repository.save(new Category(category.name));
    }

    public Flux<Category> getCategories(){
        return repository.findAll();
    }

    public Mono<Category> updateCategory(Category category){
        return repository.findById(category.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La categoria no ha sido creada")))
                .flatMap(newCategory -> repository.save(Category.updateCategory(category.id, category.name)));
    }

    public Mono<Void> deleteCategory(Category delCategory){
        return repository.findById(delCategory.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La categoria no ha sido creada")))
                .flatMap(category -> repository.deleteById(category.id));
    }

    public Mono<Category> categoryById(Category category){
        return repository.findById(category.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La categoria no ha sido creada")));
    }

    public String categoryNameById(Category category){
        return category.getName() ;
    }

    public Flux<Product> productsInCategory(String categoryName){
        Flux<Product> allProducts = productRepository.findAll();
        return allProducts.filter(product -> product.category.name.equals(categoryName));

    }

}
