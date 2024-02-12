package 디자인패턴.전략패턴;

public class Punch implements Attack{
    @Override
    public void attack() {
        System.out.println("펀치!!!");
    }
}
