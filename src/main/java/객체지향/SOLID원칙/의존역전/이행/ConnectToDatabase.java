package main.java.객체지향.SOLID원칙.의존역전.이행;

public class ConnectToDatabase {
    public void connect(JdbcUrl jdbcUrl){
        System.out.println("Connecting to " + jdbcUrl.get());
    }
}
