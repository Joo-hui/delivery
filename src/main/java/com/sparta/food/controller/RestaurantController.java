package com.sparta.food.controller;

import com.sparta.food.domain.Restaurant;
import com.sparta.food.dto.RestaurantDto;
import com.sparta.food.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.addRestaurant(restaurantDto);
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurant() {
        return restaurantService.findAllRestaurant();
    }
}
