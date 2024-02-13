package 디자인패턴.팩토리패턴.팩토리메서드;

import 디자인패턴.팩토리패턴.추상팩토리.Dough;
import 디자인패턴.팩토리패턴.추상팩토리.PizzaIngredientFactory;

public abstract class Pizza {
    String name;
    Dough dough;
    PizzaIngredientFactory pizzaIngredientFactory;

    public Pizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    void prepare(){
        dough = pizzaIngredientFactory.createDough();
        System.out.println(dough);
    }

    void bake(){
        System.out.println("175도에서 25분 간 굽기");
    }

    void cut(){
        System.out.println("피자를 사선으로 자르기");
    }

    void box(){
        System.out.println("상자에 피자 담기");
    }

    public String getName(){
        return name;
    }
}
