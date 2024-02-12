package 디자인패턴.데코레이터패턴;

public class Americano extends Beverage{
    @Override
    public String getDescription() {
        return "아메리카노";
    }

    @Override
    public double cost() {
        return 12.0;
    }
}
