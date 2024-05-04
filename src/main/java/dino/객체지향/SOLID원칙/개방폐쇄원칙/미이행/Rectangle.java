package dino.객체지향.SOLID원칙.개방폐쇄원칙.미이행;

public class Rectangle implements Shape{
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
