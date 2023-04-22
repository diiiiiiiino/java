package 디자인패턴.전략패턴.인터페이스;

public class OneFactDouble implements TerranStrategy {
    @Override
    public void run() {
        System.out.println("One Factory Double Command Center!!");
    }
}
