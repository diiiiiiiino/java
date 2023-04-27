package 디자인패턴.커맨드패턴;

public class CeilingFan {
    public enum Level { OFF, LOW, MEDIUM, HIGH }

    private Level level;

    public CeilingFan() {
        this.level = Level.OFF;
    }

    void low(){
        level = Level.LOW;
        System.out.println("CeilingFan low!!");
    }

    void medium(){
        level = Level.MEDIUM;
        System.out.println("CeilingFan medium!!");
    }

    void high(){
        level = Level.HIGH;
        System.out.println("CeilingFan high!!");
    }

    void off(){
        level = Level.OFF;
        System.out.println("CeilingFan off!!");
    }

    public Level getLevel() {
        return level;
    }
}
