package com.mondris.rabbitmqdemo.demo.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Email {

    private String toEmail;
    private String fromEmail;
    private String subject;
    private String body;
}
