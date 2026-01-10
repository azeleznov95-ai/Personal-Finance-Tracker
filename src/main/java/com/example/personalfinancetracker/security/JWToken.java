package com.example.personalfinancetracker.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@Getter
@Setter
public class JWToken {
    Long userId;
    String login;
    String passwordHash;
    LinkedList<String> roles;
}
