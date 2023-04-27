package com.example.javaspringbootstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;
}
