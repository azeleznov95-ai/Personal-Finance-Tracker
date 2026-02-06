package com.example.personalfinancetracker.repository;

import com.example.personalfinancetracker.model.Order;
import com.example.personalfinancetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByUser(User user);
    
}
