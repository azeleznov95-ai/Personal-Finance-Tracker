package com.example.clothesshop.model;

import com.example.clothesshop.enums.OrderStatus;
import com.example.clothesshop.enums.Size;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Size size;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    OrderStatus orderStatus;





}
