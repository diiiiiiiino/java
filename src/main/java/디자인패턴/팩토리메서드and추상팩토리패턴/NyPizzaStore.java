package 디자인패턴.팩토리메서드and추상팩토리패턴;

public class NyPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza() {
        PizzaIngredientFactory ingredientFactory = new NyPizzaIngredientFactory();
        return new NyPizza("뉴욕피자", ingredientFactory);
    }
}
