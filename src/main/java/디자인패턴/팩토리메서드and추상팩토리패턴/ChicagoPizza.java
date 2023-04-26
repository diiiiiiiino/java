package 디자인패턴.팩토리메서드and추상팩토리패턴;

public class ChicagoPizza extends Pizza {
    public ChicagoPizza(String name, PizzaIngredientFactory ingredientFactory) {
        super(name, ingredientFactory);
    }

    @Override
    void prepare() {
        dough = ingredientFactory.createDough();
        source = ingredientFactory.createSource();

        System.out.println(name + "를 준비 중입니다");
    }
}
