package com.example.clothesshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "clothes")
@Getter
@Setter
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    private Integer remainingAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void setCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
