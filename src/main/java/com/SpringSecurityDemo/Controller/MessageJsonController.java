package com.SpringSecurityDemo.Controller;

import com.SpringSecurityDemo.Model.User;
import com.SpringSecurityDemo.Publishers.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public  MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer)
    {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("publish")
    public ResponseEntity<String> sendMessageToQueue(@RequestBody User user){
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent successfully");
    }
}
