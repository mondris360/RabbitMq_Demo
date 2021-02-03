package com.mondris.rabbitmqdemo.demo.Consumer;

import com.mondris.rabbitmqdemo.demo.Dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    @RabbitListener(queues =  "ORDERQUEUE")
    public void ConsumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue" +  orderStatus);
    }
}
