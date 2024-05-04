package dino.객체지향.공변오버라이딩;

public class Rectangle implements Cloneable {
    @Override
    protected Rectangle clone() throws CloneNotSupportedException {
        return (Rectangle) super.clone();
    }
}
