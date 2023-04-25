package 디자인패턴.데코레이터패턴;

public class Espresso extends Beverage{
    public Espresso(){
        description = "에스프레소";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
