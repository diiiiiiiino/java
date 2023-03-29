package 모던자바인액션.model;

public class Apple extends Fruit {
    public Apple(){
        super();
    }
    public Apple(int weight) {
        super(weight);
    }

    public Apple(Color color) {
        super(color);
    }

    public Apple(int weight, Color color) {
        super(weight, color);
    }

    public Apple(int weight, Color color, int price) {
        super(weight, color, price);
    }
}
