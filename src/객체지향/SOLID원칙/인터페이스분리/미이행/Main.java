package 객체지향.SOLID원칙.인터페이스분리.미이행;

public class Main {
    public static void main(String[] args){
        WwwPingConnection www = new WwwPingConnection("www.yahoo.com");

        www.socket(); // 이 메서드는 아무 것도 하지 않지만 클라이언트는 그것을 알지 못한다.
        www.http();
        www.connect();
    }
}
