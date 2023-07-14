package com.santian.usescases;

import com.santian.adapters.RabbitMqEventPublisher;
import com.santian.repository.ProductRepository;
import com.santian.repository.model.Product;
import com.santian.repository.model.Report;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class RegisterSaleOrPurchase implements Function<Report, Mono<Report>> {

    private final ProductRepository productRepository;
    private final RabbitMqEventPublisher publisher;

    public RegisterSaleOrPurchase(ProductRepository productRepository, RabbitMqEventPublisher publisher) {
        this.productRepository = productRepository;
        this.publisher = publisher;
    }


    @Override
    public Mono<Report> apply(Report report) {
        return Mono.just(report)
                .flatMap(report1 -> {
                 Mono<Product> pr = productRepository.findById(report1.getProduct().getId());
                 pr
                 .map(product -> {
                      if (report.getType().equals("venta")) {
                          product.setAmount(product.getAmount()  - report.getQuantity());
                      }else{
                          product.setAmount(product.getAmount() + report.getQuantity());
                          }
                      productRepository.save(product);
                      return product;
                  });

                  return Mono.just(report);
                }).map(report1 -> {
                    publisher.publishTaskCreated(report1).subscribe();
                    return report1;
                })
                ;
    }
}
