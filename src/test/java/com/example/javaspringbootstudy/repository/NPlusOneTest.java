package com.example.javaspringbootstudy.repository;

import com.example.javaspringbootstudy.entity.Userr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class NPlusOneTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @DisplayName("1. N + 1 테스트")
    @Test
    void nPlusOneTest() {
        System.out.println("======================");
        List<Userr> userrs = userRepository.findAllUser();

        /*for(Userr a : userrs){
            System.out.println(a.getOrderrs().size());
        }*/
    }
}
