package 디자인패턴.데코레이터패턴;

public class ChocoChip extends CondimentDecorator{
    public ChocoChip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 초코칩";
    }

    @Override
    public double cost() {
        return beverage.cost() + .10;
    }
}
