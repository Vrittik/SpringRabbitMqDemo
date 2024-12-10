package com.SpringSecurityDemo.Publishers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    private RabbitTemplate rabbitTemplate;

    private static final Logger _logger = LoggerFactory.getLogger(RabbitMQProducer.class);

    public RabbitMQProducer(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message)
    {
        _logger.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
