package main.java.디자인패턴.어댑터패턴;

public class PlugV220 implements V220{
    @Override
    public void charge() {
        System.out.println("220V 충전");
    }
}
