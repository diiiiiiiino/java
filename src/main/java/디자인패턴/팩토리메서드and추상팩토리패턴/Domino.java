package 디자인패턴.팩토리메서드and추상팩토리패턴;

public class Domino {
    public static void main(String[] args){
        PizzaStore pizzaStore = new NyPizzaStore();
        pizzaStore.orderPizza();

        pizzaStore = new ChicagoPizzaStore();
        pizzaStore.orderPizza();
    }
}
