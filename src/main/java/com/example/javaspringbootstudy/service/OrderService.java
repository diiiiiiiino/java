package com.example.javaspringbootstudy.service;

import com.example.javaspringbootstudy.entity.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
