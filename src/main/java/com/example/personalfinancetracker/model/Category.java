package com.example.personalfinancetracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name="Categories")
@Entity
@Getter
@Setter
public class Category {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = true)
    private String category;
    @Column()
    private String description;



}
