package com.sparta.food.controller;

import com.sparta.food.dto.OrdersRequestDto;
import com.sparta.food.dto.OrdersResponseDto;
import com.sparta.food.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrdersResponseDto orderFood(@RequestBody OrdersRequestDto ordersRequestDto) {
        return orderService.order(ordersRequestDto);
    }

    @GetMapping("/orders")
    public List<OrdersResponseDto> findAllOrder() {
        return orderService.findAllOrder();
    }
}
