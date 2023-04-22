package 디자인패턴.전략패턴.인터페이스;

public class EightBarracks implements TerranStrategy {
    @Override
    public void run() {
        System.out.println("BBS!!");
    }
}
