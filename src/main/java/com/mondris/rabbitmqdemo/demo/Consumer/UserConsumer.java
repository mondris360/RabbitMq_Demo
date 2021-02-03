package com.mondris.rabbitmqdemo.demo.Consumer;


import com.mondris.rabbitmqdemo.demo.Dto.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @RabbitListener(queues = "USERQUEUE")
    public void ConsumeUserFromQueue(User user){
        System.out.println("User Message Received From Queue" +  user);
    }
}
