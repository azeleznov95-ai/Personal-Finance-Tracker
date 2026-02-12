package com.example.clothesshop.repository;

import com.example.clothesshop.model.Orders;
import com.example.clothesshop.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findOrderByUser(Users user);
    
}
