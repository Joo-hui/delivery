package com.sparta.food.service;

import com.sparta.food.domain.Food;
import com.sparta.food.domain.Restaurant;
import com.sparta.food.dto.FoodDto;
import com.sparta.food.repository.FoodRepository;
import com.sparta.food.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void addRestaurantFoods(Long restaurantId, List<FoodDto> requestDtoList) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);

        checkRestaurant(foundRestaurant);
        Restaurant restaurant = foundRestaurant.get();

        for (FoodDto foodDto : requestDtoList) {
            String foodName = foodDto.getName();
            int foodPrice = foodDto.getPrice();

            checkDuplicateRestaurantFood(restaurant, foodName);

            checkFoodPrice(foodPrice);

            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);

        }

    }

    private void checkRestaurant(Optional<Restaurant> foundRestaurant) {
        if (!foundRestaurant.isPresent())
            throw new IllegalStateException("음식점 존재하지 않음");
    }

    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName) {
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, foodName);
        if (found.isPresent())
            throw new IllegalStateException("음식이 존재합니다.");
    }


    private void checkFoodPrice(int foodPrice) {
        if (foodPrice < 100)
            throw new IllegalArgumentException("음식 가격 100원 이상 입력해주세요");

        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException("음식 가격은 1,000,000원 이하로 입력해주세요");

        if (foodPrice % 100 > 0)
            throw new IllegalArgumentException("음식 가격 100원 단위로 입력됨");
    }


        @Transactional
        public List<Food> findAllRestaurantFoods(Long restaurantId) {
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(
                            () -> new NullPointerException("음식점 존재하지 않음"));

            return foodRepository.findFoodsByRestaurant(restaurant);

    }
}


