package com.mondris.rabbitmqdemo.demo.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;
}
