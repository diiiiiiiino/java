package dino.객체지향.오버로딩;

import java.io.IOException;
import java.rmi.RemoteException;

public class Parent {
    /**
     * private, static, fianl 메서드 오버라이딩 가능
     */
    private String method(String name){
        return name;
    }
    private void method(String name, String age){}

    static void staticMethod(String name){}
    static void staticMethod(String name, String age){}

    final void finalMethod(String name){}
    final void finalMethod(String name, String age){}

    void checkedExceptionMethod() throws IOException {}
    void checkedExceptionMethod(String name) throws RemoteException {}

    void uncheckedExceptionMethod() throws RuntimeException {}
    void uncheckedExceptionMethod(String name) throws NullPointerException {}
}
