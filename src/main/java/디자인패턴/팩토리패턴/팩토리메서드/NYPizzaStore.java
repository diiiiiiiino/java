package main.java.디자인패턴.팩토리패턴.팩토리메서드;

import main.java.디자인패턴.팩토리패턴.추상팩토리.NyPizzaIngredientFactory;
import main.java.디자인패턴.팩토리패턴.추상팩토리.PizzaIngredientFactory;

public class NYPizzaStore extends PizzaStore{
    PizzaIngredientFactory ingredientFactory = new NyPizzaIngredientFactory();

    @Override
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new NYStyleCheesePizza(ingredientFactory);
        }

        return null;
    }
}
