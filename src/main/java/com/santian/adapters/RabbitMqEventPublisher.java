package com.santian.adapters;

import com.santian.configuration.RabbitMqConfig;
import com.santian.repository.model.Report;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.OutboundMessageResult;
import reactor.rabbitmq.Sender;

@Component
public class RabbitMqEventPublisher {
    private final Sender sender;

    public RabbitMqEventPublisher(Sender sender) {
        this.sender = sender;
    }

    public Flux<OutboundMessageResult> publishTaskCreated(Report report){
        return sender.sendWithPublishConfirms(
                Mono.just(new OutboundMessage(RabbitMqConfig.TOPIC_EXCHANGE,
                        RabbitMqConfig.ROUTING_KEY_EVENTS_GENERAL,
                        report.toString().getBytes())));
    }
}
