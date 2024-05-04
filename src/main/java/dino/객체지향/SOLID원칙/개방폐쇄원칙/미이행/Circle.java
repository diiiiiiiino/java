package dino.객체지향.SOLID원칙.개방폐쇄원칙.미이행;

public class Circle implements Shape{
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
