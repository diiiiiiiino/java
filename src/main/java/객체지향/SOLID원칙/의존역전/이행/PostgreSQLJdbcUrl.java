package main.java.객체지향.SOLID원칙.의존역전.이행;

public class PostgreSQLJdbcUrl implements JdbcUrl {
    private final String dbName;

    public PostgreSQLJdbcUrl(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String get() {
        return "jdbc:postgresql:// ..." + this.dbName;
    }
}
