package com.mondris.rabbitmqdemo.demo.Controller;

import com.mondris.rabbitmqdemo.demo.Dto.Email;
import com.mondris.rabbitmqdemo.demo.Dto.User;
import com.mondris.rabbitmqdemo.demo.Util.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    // not an idea situation  to use queue. I am just playing with it
    @PostMapping("/user")
    public String createUser(@RequestBody User request) {
        rabbitTemplate.convertAndSend(Constants.MYEXCHANGE.toString(), Constants.USERQUEUE.toString(), request);
        return "User Created";
    }

    @PostMapping("/user/email")
    public  String SendEmail(@RequestBody Email request){
        System.out.println("email" +  request);
        rabbitTemplate.convertAndSend(Constants.MYEXCHANGE.toString(), Constants.EMAILQUEUE.toString(), request);
        return  "Email Sent";
    }
}
