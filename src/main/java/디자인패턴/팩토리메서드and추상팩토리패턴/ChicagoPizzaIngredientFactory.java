package 디자인패턴.팩토리메서드and추상팩토리패턴;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory{
    @Override
    public String createDough() {
        return "ChicagoDough";
    }

    @Override
    public String createSource() {
        return "ChicagoSource";
    }
}
