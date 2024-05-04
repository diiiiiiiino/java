package dino.디자인패턴.어댑터패턴;

public class Main {
    public static void main(String[] args) {
        PlugV220 plugV220 = new PlugV220();
        PlugV110 plugV110 = new PlugV110();
        PlugV110Adapter adapter = new PlugV110Adapter(plugV220);

        System.out.print("adapter 호출 : ");
        V110PowerSocket v110PowerSocket = new V110PowerSocket(adapter);
        v110PowerSocket.charge();

        System.out.print("일반 호출 : ");
        v110PowerSocket = new V110PowerSocket(plugV110);
        v110PowerSocket.charge();
    }
}
