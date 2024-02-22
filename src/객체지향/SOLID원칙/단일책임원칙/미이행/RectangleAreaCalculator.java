package 객체지향.SOLID원칙.단일책임원칙.미이행;

public class RectangleAreaCalculator {
    private static final double INCH_TERM = 0.0254d;

    private final int width;
    private final int height;

    public RectangleAreaCalculator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int area() {
        return width * height;
    }

    //단일 책임 원칙에 맞지 않는 메서드
    public double metersToInches(int area){
        return area / INCH_TERM;
    }
}
