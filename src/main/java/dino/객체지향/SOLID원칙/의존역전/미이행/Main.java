package dino.객체지향.SOLID원칙.의존역전.미이행;

public class Main {
    public static void main(String[] args){
        ConnectToDatabase connectToDatabase = new ConnectToDatabase();

        PostgreSQLJdbcUrl postgreSQLJdbcUrl = new PostgreSQLJdbcUrl("AA");
        connectToDatabase.connect(postgreSQLJdbcUrl);

        MySQLJdbcUrl mySQLJdbcUrl = new MySQLJdbcUrl("BB");
        connectToDatabase.connect(mySQLJdbcUrl);
    }
}
