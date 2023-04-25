package 디자인패턴.데코레이터패턴;

public class HouseBlend extends Beverage{
    public HouseBlend(){
        description = "하우스 블렌드 커피";
    }

    @Override
    public double cost() {
        return .99;
    }
}
