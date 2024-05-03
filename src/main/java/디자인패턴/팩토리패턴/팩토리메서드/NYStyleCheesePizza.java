package main.java.디자인패턴.팩토리패턴.팩토리메서드;

import main.java.디자인패턴.팩토리패턴.추상팩토리.PizzaIngredientFactory;

public class NYStyleCheesePizza extends Pizza{
    public NYStyleCheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        super(pizzaIngredientFactory);
        name = "뉴욕 스타일 소스와 치즈 피자";
    }
}
