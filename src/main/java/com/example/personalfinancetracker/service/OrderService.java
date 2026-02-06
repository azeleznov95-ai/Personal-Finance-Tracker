package com.example.personalfinancetracker.service;

import com.example.personalfinancetracker.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository orderRepository;
    OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    public void setOrderFromCart ()
}
