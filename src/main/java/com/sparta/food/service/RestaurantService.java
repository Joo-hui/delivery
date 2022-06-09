package com.sparta.food.service;

import com.sparta.food.domain.Restaurant;
import com.sparta.food.dto.RestaurantDto;
import com.sparta.food.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(RestaurantDto restaurantDto) {
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        checkMinOrderPrice(minOrderPrice);

        checkDeliveryFee(deliveryFee);

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    private void checkMinOrderPrice(int minOrderPrice) {
        if(!(1000 <= minOrderPrice && minOrderPrice <= 100000)) {
            throw new IllegalArgumentException("1000원 이상 100000원 이하로 입력해주세요");
        }

        if(minOrderPrice % 100 > 0) {
            throw new IllegalArgumentException("100원 단위로만 입력이 가능합니다.");
        }
    }

    private void checkDeliveryFee(int deliveryFee) {
        if(0 > deliveryFee || deliveryFee > 10_000) {
            throw new IllegalArgumentException("0원 이상 10000원 이하로 입력 해주세요");
        }

        if(deliveryFee % 500 > 0) {
            throw new IllegalArgumentException("500원 단위로만 입력이 가능합니다.");
        }
    }

    @Transactional
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }

}
