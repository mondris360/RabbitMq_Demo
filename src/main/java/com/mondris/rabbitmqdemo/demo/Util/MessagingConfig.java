package com.mondris.rabbitmqdemo.demo.Util;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public Queue orderQueue(){

        return new Queue(Constants.ORDERQUEUE.toString());
    }

    @Bean
    public Queue userQueue(){

        return new Queue(Constants.USERQUEUE.toString());
    }

    @Bean
    public Queue emailQueue(){

        return new Queue(Constants.EMAILQUEUE.toString());
    }

    @Bean
    public TopicExchange exchange(){

        return new TopicExchange(Constants.MYEXCHANGE.toString());
    }

   // bind the queue to the exchange
   @Bean
    public Binding orderQueueBinding(TopicExchange topicExchange){

       return   BindingBuilder.bind(orderQueue()).to(topicExchange).with(Constants.ORDERQUEUEROUTINGKEY.toString());
    }

    @Bean
    public Binding userQueueQueueBinding(TopicExchange topicExchange){

        return   BindingBuilder.bind(userQueue()).to(topicExchange).with(Constants.USERQUEUE.toString());
    }

    @Bean
    public Binding emailQueueBinding(TopicExchange topicExchange){

        return   BindingBuilder.bind(emailQueue()).to(topicExchange).with(Constants.EMAILQUEUE.toString());
    }

    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }

    // rabbitmq template
    @Bean
   public AmqpTemplate  amqpTemplate(ConnectionFactory connectionFactory) {

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
   }
}
