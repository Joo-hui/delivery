package com.sparta.food.controller;

import com.sparta.food.domain.Food;
import com.sparta.food.dto.FoodDto;
import com.sparta.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodList) {

        foodService.addRestaurantFoods(restaurantId, foodList);
    }
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllRestaurantFoods(
            @PathVariable Long restaurantId
    ) {
        return foodService.findAllRestaurantFoods(restaurantId);
    }

}
