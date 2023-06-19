package EffectiveJava.chap03;

import 모던자바인액션.model.Color;

import java.util.Objects;

public class ColorPoint{
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color){
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint(){
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) obj;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
