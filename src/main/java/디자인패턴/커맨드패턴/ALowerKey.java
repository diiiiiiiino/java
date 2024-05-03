package main.java.디자인패턴.커맨드패턴;

public class ALowerKey implements Key {
    @Override
    public void push() {
        System.out.println("a키 눌림!!");
    }
}
