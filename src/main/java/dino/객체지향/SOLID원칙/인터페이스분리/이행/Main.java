package dino.객체지향.SOLID원칙.인터페이스분리.이행;

public class Main {
    public static void main(String[] args){
        WwwPingConnection www = new WwwPingConnection("www.naver.com");
        www.http();
        www.connect();
    }
}
