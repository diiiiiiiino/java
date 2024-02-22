package 객체지향.다형성;

public class Main {
    public static void main(String[] args){
        Shape triangle = new Triangle();
        Shape rectangle = new Rectangle();
        Shape circle = new Circle();

        triangle.draw();
        rectangle.draw();
        circle.draw();
    }
}
