package EffectiveJava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProgrammingTests {
    @Test
    @DisplayName("아이템60. 정확한 답이 필요하다면 float와 double은 피하라")
    void item60(){
        double funds = 1.00;
        int itemsBought = 0;
        for(double price = 0.10; funds >= price; price += 0.10){
            funds -= price;
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(달러): " + funds);
    }

    @Test
    @DisplayName("아이템60. 정확한 답이 필요하다면 float와 double은 피하라 (BigDecimal 사용)")
    void item60_2(){
        final BigDecimal TEN_CENTS = new BigDecimal(".10");

        BigDecimal funds = new BigDecimal("1.00");
        int itemsBought = 0;
        for(BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)){
            funds = funds.subtract(price);
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(달러): " + funds);
    }

    @Test
    @DisplayName("아이템60. 정확한 답이 필요하다면 float와 double은 피하라 (int 사용)")
    void item60_3(){
        int funds = 100;
        int itemsBought = 0;
        for(int price = 10; funds >= price; price += 10){
            funds -= price;
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(달러): " + funds);
    }
}
