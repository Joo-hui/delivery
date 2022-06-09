package com.sparta.food.dto;

import com.sparta.food.domain.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {

    private Long restaurantId;
    private List<OrderItem> foods;
}
