package dino.디자인패턴.어댑터패턴;

public class PlugV110 implements V110{
    @Override
    public void charge() {
        System.out.println("110V 충전");
    }
}
