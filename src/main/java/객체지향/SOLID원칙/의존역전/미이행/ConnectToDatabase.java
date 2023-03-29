package 객체지향.SOLID원칙.의존역전.미이행;

public class ConnectToDatabase {
    public void connect(PostgreSQLJdbcUrl postgresql){
        System.out.println("Connecting to " + postgresql.get());
    }

    public void connect(MySQLJdbcUrl mySQLJdbcUrl){
        System.out.println("Connecting to " + mySQLJdbcUrl.get());
    }
}
