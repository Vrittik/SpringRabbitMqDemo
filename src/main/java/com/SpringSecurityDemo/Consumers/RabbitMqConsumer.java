package com.SpringSecurityDemo.Consumers;

import com.SpringSecurityDemo.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
    private static final Logger _logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(User user)
    {
        _logger.info(String.format("Received message %s", user.toString()));
    }
}
