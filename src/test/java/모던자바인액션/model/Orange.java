package 모던자바인액션.model;

public class Orange extends Fruit {
    public Orange(){
        super();
    }
    public Orange(int weight) {
        super(weight);
    }

    public Orange(Color color) {
        super(color);
    }

    public Orange(int weight, Color color) {
        super(weight, color);
    }

    public Orange(int weight, Color color, int price) {
        super(weight, color, price);
    }
}
