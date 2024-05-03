package main.java.디자인패턴.싱글톤패턴;

public class Singleton1 {
    private static Singleton1 instance = new Singleton1();

    private Singleton1(){}

    public Singleton1 getInstance(){
        return instance;
    }
}
