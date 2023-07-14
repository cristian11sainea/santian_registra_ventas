package com.santian.routes;

import com.santian.repository.model.Product;
import com.santian.repository.model.Report;
import com.santian.usescases.RegisterSaleOrPurchase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RegisterRoutes {


   /* @Bean
    public RouterFunction<ServerResponse> registerSale(RegisterSaleOrPurchase registerSaleOrPurchase){
        return route(POST("route/register/sale"),
                request -> request.bodyToMono(Report.class)
                        .flatMap(report -> registerSaleOrPurchase.apply(report))
                        .flatMap(result -> ServerResponse.ok().bodyValue(result)));
    }*/
}
