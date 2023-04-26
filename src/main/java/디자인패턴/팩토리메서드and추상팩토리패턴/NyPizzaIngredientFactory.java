package 디자인패턴.팩토리메서드and추상팩토리패턴;

public class NyPizzaIngredientFactory implements PizzaIngredientFactory{
    @Override
    public String createDough() {
        return "NyDough";
    }

    @Override
    public String createSource() {
        return "NySource";
    }
}
