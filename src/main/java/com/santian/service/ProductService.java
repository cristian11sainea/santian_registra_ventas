package com.santian.service;

import com.santian.repository.ProductRepository;
import com.santian.repository.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Mono<Product> addProduct(Product product ){
        return repository.save(new Product(product.name, product.category,
                            product.unitValue, product.saleValue, product.amount, product.user));


    }

    public Flux<Product> getTasks(){
        return repository.findAll();
    }


}
