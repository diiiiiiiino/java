package dino.디자인패턴.싱글톤패턴;

/**
 * Double-check locking 기법
 */
public class Singleton3 {
    private Singleton3 instance;

    private Singleton3(){}

    public Singleton3 getInstance() {
        if(instance == null){
            synchronized (Singleton3.class) {
                if(instance == null){
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }
}
