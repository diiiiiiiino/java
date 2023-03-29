package 객체지향.다형성;

public class Triangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Draw a triangle...");
    }
}
