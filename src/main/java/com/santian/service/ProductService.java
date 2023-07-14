package com.santian.service;

import com.santian.repository.ProductRepository;
import com.santian.repository.model.Product;
import com.santian.repository.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Mono<Product> updateProduct(Product product){
        return repository.findById(product.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El registro no esta en la base de datos")))
                .flatMap(product1 -> repository.save(product));
    }

    public Mono<Void> deleteTask(Product product){
        return repository.findById(product.id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El registro no esta en la base de datos")))
                .flatMap(product1 -> repository.deleteById(product.id));
    }


    public Mono<Report> reportes(Report report) {
        return Mono.just(report)
                .flatMap(report1 -> {
                    Mono.just(report.getProduct())
                            .map(product -> {
                                if (report.getType().equals("venta")) {
                                    product.setAmount(product.getAmount() - report.getQuantity());
                                }else{
                                    product.setAmount(product.getAmount() + report.getQuantity());
                                }
                                repository.save(product);
                                return product;
                            });

                    return Mono.just(report);
                }).map(report1 -> {
                    //publisher.publishTaskCreated(report1).subscribe();
                    return report1;
                })
                ;
    }


}
