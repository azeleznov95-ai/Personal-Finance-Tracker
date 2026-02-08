package com.example.clothesshop.model;

import com.example.clothesshop.enums.Size;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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
    @Column(name="price",nullable = false)
    private Integer price;
    @Column(name = "description",nullable = true)
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "clothes_sizes", joinColumns = @JoinColumn(name = "clothes_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    Set<Size> setSize;
    @Column(name = "imageUrl")
    String imageUrl;
    @Column(name = "active")
    boolean active=true;
    @Column(name="remainingAmount",nullable = false)
    private Integer remainingAmount=0;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void setCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
