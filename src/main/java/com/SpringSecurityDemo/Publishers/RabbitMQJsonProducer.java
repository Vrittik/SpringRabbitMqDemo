package com.SpringSecurityDemo.Publishers;

import com.SpringSecurityDemo.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.routing_json.key}")
    private String routingJsonKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    private RabbitTemplate rabbitTemplate;

    private static final Logger _logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        _logger.info(String.format("Json message sent: %S", user.toString()));
        rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, user);
    }
}
