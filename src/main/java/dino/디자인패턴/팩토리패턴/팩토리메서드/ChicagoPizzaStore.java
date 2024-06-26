package dino.디자인패턴.팩토리패턴.팩토리메서드;

import dino.디자인패턴.팩토리패턴.추상팩토리.ChicagoPizzaIngredientFactory;
import dino.디자인패턴.팩토리패턴.추상팩토리.PizzaIngredientFactory;

public class ChicagoPizzaStore extends PizzaStore{
    PizzaIngredientFactory pizzaIngredientFactory = new ChicagoPizzaIngredientFactory();

    @Override
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new ChicagoStyleCheesePizza(pizzaIngredientFactory);
        }

        return null;
    }
}
