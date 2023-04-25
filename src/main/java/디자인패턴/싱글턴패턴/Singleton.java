package 디자인패턴.싱글턴패턴;

public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
}
