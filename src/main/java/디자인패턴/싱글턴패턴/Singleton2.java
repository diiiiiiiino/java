package 디자인패턴.싱글턴패턴;

public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2(){}

    public static synchronized Singleton2 getInstance(){ //멀티스레드 문제는 해결되지만 동기화를 하면 속도가 100배 저하된다.
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
