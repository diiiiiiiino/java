package main.java.디자인패턴.커맨드패턴;

public class BLowerKey implements Key {
    @Override
    public void push() {
        System.out.println("b키 눌림!!");
    }
}
