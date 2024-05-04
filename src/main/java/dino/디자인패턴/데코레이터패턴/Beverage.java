package dino.디자인패턴.데코레이터패턴;

public abstract class Beverage {
    private String description = "제목 없음";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}
