package main.java.디자인패턴.전략패턴;

public class LowKick implements Attack{
    @Override
    public void attack() {
        System.out.println("로우킥!!!");
    }
}
