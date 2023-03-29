package 객체지향.SOLID원칙.의존역전.미이행;

public class PostgreSQLJdbcUrl {
    private final String dbName;

    public PostgreSQLJdbcUrl(String dbName) {
        this.dbName = dbName;
    }

    public String get() {
        return "jdbc:postgresql:// ..." + this.dbName;
    }
}
