package dino.객체지향.SOLID원칙.의존역전.미이행;

public class MySQLJdbcUrl {
    private final String dbName;

    public MySQLJdbcUrl(String dbName) {
        this.dbName = dbName;
    }

    public String get() {
        return "jdbc:mysql:// ..." + this.dbName;
    }
}
