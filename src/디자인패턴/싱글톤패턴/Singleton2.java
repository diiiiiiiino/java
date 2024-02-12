package 디자인패턴.싱글톤패턴;

/**
 * synchronized 동기화
 * 성능이 100배 저하됨
 */
public class Singleton2 {
    private Singleton2 instance;

    private Singleton2(){}

    public synchronized Singleton2 getInstance() {

        if(instance == null){
            return new Singleton2();
        }

        return instance;
    }
}
