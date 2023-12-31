package com.santian.configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.*;

@Configuration
public class RabbitMqConfig {

    public static final String TOPIC_EXCHANGE = "report-topic-exchange";
    public static final String QUEUE_EVENTS_GENERAL = "report.events.general";
    public static final String ROUTING_KEY_EVENTS_GENERAL = "routingkey.report.events.#";


    @Bean
    public Mono<Connection> createConnectionToRabbit(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        return Mono.fromCallable(()->connectionFactory.newConnection("events-handler"));
    }

    @Bean
    public SenderOptions senderOptions(Mono<Connection> connectionMono) {
        return new SenderOptions()
                .connectionMono(connectionMono)
                .resourceManagementScheduler(Schedulers.boundedElastic());
    }

    @Bean
    public Sender createSender(SenderOptions options){
        return RabbitFlux.createSender(options);
    }


    @Bean
    public ReceiverOptions receiverOptions(Mono<Connection> connectionMono) {
        return new ReceiverOptions()
                .connectionMono(connectionMono);
    }

    @Bean
    public Receiver createReceiver(ReceiverOptions options){
        return RabbitFlux.createReceiver(options);
    }
}
