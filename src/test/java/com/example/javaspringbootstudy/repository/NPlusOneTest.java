package com.example.javaspringbootstudy.repository;

import com.example.javaspringbootstudy.entity.OrderEager;
import com.example.javaspringbootstudy.entity.OrderLazy;
import com.example.javaspringbootstudy.entity.UserEager;
import com.example.javaspringbootstudy.entity.UserLazy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@DataJpaTest
public class NPlusOneTest {

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void init(){
        for(int i = 1; i <= 5; i++){
            UserEager userEager = UserEager.builder()
                    .name("eagerUser" + i)
                    .build();

            OrderEager orderEager = OrderEager.builder()
                    .item("eagerItem" + i)
                    .user(userEager)
                    .build();

            UserLazy userLazy = UserLazy.builder()
                    .name("lazyUser" + i)
                    .build();

            OrderLazy orderLazy = OrderLazy.builder()
                    .item("lazyItem" + i)
                    .user(userLazy)
                    .build();

            entityManager.persist(userEager);
            entityManager.persist(userLazy);
            entityManager.persist(orderEager);
            entityManager.persist(orderLazy);
        }

        entityManager.clear(); //영속성 컨텍스트를 초기화하지 않으면 1차캐시에서 데이터를 가져오기때문에 정확한 쿼리 호출 확인이 불가함
    }

    @DisplayName("1-1. N + 1 테스트 (FetchType.EAGER 일때)")
    @Test
    void nPlusOneEager1_1() {
        List<UserEager> results = entityManager.createQuery(
                " select a " +
                        " from UserEager a", UserEager.class)
                .getResultList();
    }

    @DisplayName("1-2. N + 1 테스트 (FetchType.EAGER 일때 해결방법 : join fetch)")
    @Test
    void nPlusOneEager1_2() {
        List<UserEager> results = entityManager.createQuery(
                        " select a " +
                                " from UserEager a " +
                                " join fetch a.orders ", UserEager.class)
                .getResultList();
    }

    @DisplayName("2-1. N + 1 테스트 (FetchType.LAZY 일 때)")
    @Test
    void nPlusOneLazy2_1() {
        List<UserLazy> results = entityManager.createQuery(
                " select a " +
                        " from UserLazy a", UserLazy.class)
                .getResultList();

        for(UserLazy user : results){
            user.getOrders().size();
        }
    }

    @DisplayName("2-2. N + 1 테스트 (FetchType.LAZY 일 때 해결방법 : join fetch)")
    @Test
    void nPlusOneLazy2_2() {
        List<UserLazy> results = entityManager.createQuery(
                        " select a " +
                                " from UserLazy a" +
                                " join fetch a.orders ", UserLazy.class)
                .getResultList();

        for(UserLazy user : results){
            user.getOrders().size();
        }
    }
}
