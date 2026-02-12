package com.example.clothesshop.model;

import com.example.clothesshop.enums.OrderStatusEnum;
import com.example.clothesshop.enums.SizeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Users user;
    @Column(nullable = false)
    private String orderedCloth;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SizeEnum size;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private OrderStatusEnum orderStatus;





}
