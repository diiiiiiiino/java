package dino.객체지향.오버라이딩;

import java.io.IOException;

public class Parent {
    final public void finalMethod(){} //오버라이딩 불가
    static public void staticMethod(){} //오버라이딩 불가
    private void privateMethod(){} //오버라이딩 불가
    void defaultMethod(){} //오버라이딩 가능
    void checkedExceptionMethod() throws IOException {} //오버라이딩 가능
    void uncheckedExceptionMethod() throws IllegalArgumentException {}
}
