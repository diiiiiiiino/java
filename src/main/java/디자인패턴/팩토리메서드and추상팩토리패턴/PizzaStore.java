package 디자인패턴.팩토리메서드and추상팩토리패턴;

public abstract class PizzaStore {
    public void orderPizza(){
        Pizza pizza = createPizza();

        pizza.prepare();
    }

    abstract protected Pizza createPizza();
}
