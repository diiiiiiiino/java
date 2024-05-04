package dino.객체지향.SOLID원칙.개방폐쇄원칙.이행;

public class Circle implements Shape {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    public int getRadius() {
        return radius;
    }
}
