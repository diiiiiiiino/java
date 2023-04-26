package 디자인패턴.팩토리메서드and추상팩토리패턴;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza() {
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

        return new ChicagoPizza("시카고 피자", ingredientFactory);
    }
}
