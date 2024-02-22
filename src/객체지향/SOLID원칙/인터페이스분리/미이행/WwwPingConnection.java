package 객체지향.SOLID원칙.인터페이스분리.미이행;

public class WwwPingConnection implements Connection {
    private final String www;

    public WwwPingConnection(String www) {
        this.www = www;
    }

    @Override
    public void http() {
        System.out.println("Setup an HTTP connection to " + www);
    }

    @Override
    public void connect() {
        System.out.println("Connect to " + www);
    }

    //이 메서드는 인터페이스 분리 원칙에 맞지 않습니다
    //이 클래스는 socket 메서드가 필요하지 않지만 강제로 재정의해야 합니다
    @Override
    public void socket() {

    }
}
