package com.example.personalfinancetracker.model;

import com.example.personalfinancetracker.enums.OrderStatus;
import com.example.personalfinancetracker.enums.Size;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(nullable = false)
    String orderedCloth;
    @Column(nullable = false)
    LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
    @Column(nullable = false)
    Size size;
    @Column(nullable = true)
    OrderStatus orderStatus;





}
