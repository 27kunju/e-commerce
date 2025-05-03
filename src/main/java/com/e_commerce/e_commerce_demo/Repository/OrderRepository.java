package com.e_commerce.e_commerce_demo.Repository;

import com.e_commerce.e_commerce_demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
