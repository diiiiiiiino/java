package 객체지향.SOLID원칙.의존역전.이행;

public class MySQLJdbcUrl implements JdbcUrl {
    private final String dbName;

    public MySQLJdbcUrl(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String get() {
        return "jdbc:mysql:// ..." + this.dbName;
    }
}
