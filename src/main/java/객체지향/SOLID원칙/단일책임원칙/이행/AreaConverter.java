package main.java.객체지향.SOLID원칙.단일책임원칙.이행;

public class AreaConverter {
    private static final double INCH_TERM = 0.0254d;
    private static final double FEET_TERM = 0.3048d;

    public double metersToInches(int area){
        return area / INCH_TERM;
    }

    public double metersToFeet(int area){
        return area / FEET_TERM;
    }
}
