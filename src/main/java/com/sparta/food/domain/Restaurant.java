package com.sparta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity // db 테이블
@Getter //접근자 자동 생성
@AllArgsConstructor     //모든 필드 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor  //파라미터가 없는 기본 생성자를 생성
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성시 @id @GeneratedValue 같이 생성 해줘야 db가 자동으로 AUTO_INCREMENT 해줌
    private Long id;

    @Column(nullable = false)
    private String name;    //음식점 이름

    @Column(nullable = false)
    private int minOrderPrice;   //최소 주문 가격 (허용값: 1,000원 ~ 100,000원 입력)

    @Column(nullable = false)
    private int deliveryFee;    //기본 배달비 (허용값: 0원 ~ 10,000원)


}
