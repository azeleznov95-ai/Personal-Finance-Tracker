package com.example.personalfinancetracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "FinanceTransactions")
@Getter
@Setter
public class FinanceTransaction {
    @Column(unique = true, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = false, name = "value",nullable = false)
    int value;
    @ManyToOne
    @JoinColumn(unique = false, name = "category",nullable = true)
    private Category category;
    @Column(unique = false,nullable = true,name = "purchase")

    String purchase;
    @Column(unique = true,nullable = true,name="description")
    String description;

}
