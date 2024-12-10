package com.SpringSecurityDemo.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing_json.key}")
    private String routingKey;

    // Internally Rabbit template makes use of Queue, that's why Queue
    // bean is there
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    // Internally Rabbit template makes use of TopicExchange, that's why topic exchange
    // bean is there
    // spring bean for exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    // Internally Rabbit template makes use of Binding, that's why binding
    // bean is there
    // binding queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // Used in amqp template bean for defining rabbit template
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    // @Bean method returning AmqpTemplate:
    // In your RabbitMQConfig class,
    // you're defining a bean of type AmqpTemplate,
    // but you're returning an instance of
    // RabbitTemplate from the bean method.
    // Since RabbitTemplate implements
    // AmqpTemplate
    // this is perfectly fine. This bean configuration means
    // that whenever Spring needs an AmqpTemplate, it will get a RabbitTemplate.
    // This is fine because  => RabbitTemplate Implements AmqpTemplate
    // this is done as part of extraction of RabbitTemplate
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return  rabbitTemplate;
    }
}
