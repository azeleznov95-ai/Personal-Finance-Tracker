package com.example.personalfinancetracker.model;

import com.example.personalfinancetracker.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String login;

        @Column(nullable = false)
        private String passwordHash;
        @Column(nullable = false,unique = true)
        private String telegramUsername;
        @Column(nullable = true,unique = true)
        private  Long telegramId;
        @Column(nullable = false)
        private Roles role;

}
