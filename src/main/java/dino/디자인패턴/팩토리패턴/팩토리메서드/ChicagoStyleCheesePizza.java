package dino.디자인패턴.팩토리패턴.팩토리메서드;

import dino.디자인패턴.팩토리패턴.추상팩토리.PizzaIngredientFactory;

public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        super(pizzaIngredientFactory);
        name = "시카고 스타일 딥 디쉬 치즈 피자";
    }

    @Override
    void cut(){
        System.out.println("네모난 모양으로 피자 자르기");
    }
}
