package com.mondris.rabbitmqdemo.demo.Controller;

import com.mondris.rabbitmqdemo.demo.Dto.Order;
import com.mondris.rabbitmqdemo.demo.Dto.OrderStatus;
import com.mondris.rabbitmqdemo.demo.Publisher.OrderPublisher;
import com.mondris.rabbitmqdemo.demo.Util.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/order/{restaurantName}")
    public  String ProcessOrder(@RequestBody Order order, @PathVariable String restaurantName) {

        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus = new OrderStatus(order, "Submitted", "Your order has been submitted" +
                " Successfully in " +  restaurantName );

        // publish the message to the exchange
        rabbitTemplate.convertAndSend(Constants.MYEXCHANGE.toString(), Constants.ORDERQUEUEROUTINGKEY.toString(), orderStatus);
        return "Order Submitted Successfully";

    }
}
