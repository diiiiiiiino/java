package dino.객체지향.SOLID원칙.개방폐쇄원칙.이행;

import java.util.List;

public class AreaCalculator {
    private final List<Shape> shapes;

    public AreaCalculator(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public double sum(){
        int sum = 0;
        for(Shape shape : shapes){
            sum += shape.area();
        }

        return sum;
    }
}
