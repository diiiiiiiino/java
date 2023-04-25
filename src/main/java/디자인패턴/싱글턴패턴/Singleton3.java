package 디자인패턴.싱글턴패턴;

public class Singleton3 {
    private volatile static Singleton3 instance;

    private Singleton3(){}

    //DCL (Double-Checked Locking)
    public static Singleton3 getInstance(){
        if(instance == null){
            synchronized (Singleton3.class){
                if(instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
