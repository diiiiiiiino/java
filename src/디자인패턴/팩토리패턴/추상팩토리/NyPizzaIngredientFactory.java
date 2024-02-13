package 디자인패턴.팩토리패턴.추상팩토리;

public class NyPizzaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough(){
        return new ThinCrustDough();
    }
}
