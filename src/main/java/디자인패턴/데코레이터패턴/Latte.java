package main.java.디자인패턴.데코레이터패턴;

public class Latte extends Beverage{
    @Override
    public String getDescription() {
        return "라떼";
    }

    @Override
    public double cost() {
        return 13.0;
    }
}
