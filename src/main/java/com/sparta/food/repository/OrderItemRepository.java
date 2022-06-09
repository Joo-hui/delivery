package com.sparta.food.repository;

import com.sparta.food.domain.OrderItem;
import com.sparta.food.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findOrderItemsByOrders(Orders orders);
}
