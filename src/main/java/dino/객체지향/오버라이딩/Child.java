package dino.객체지향.오버라이딩;

import java.rmi.RemoteException;

public class Child extends Parent{

    /**
     * 1. static이 아닌 메서드는 static 메서드로 오버라이드 불가, 반대도 불가
     * 2. 오버라이드 메서드보다 접근성을 감소시킬 수 없다
     */
    @Override
    /*static*/ /*private*/ void defaultMethod() {
    }

    @Override
    void checkedExceptionMethod() throws RemoteException /*throws Exception*/ { // 오버라이드 메서드보다 상위 예외를 발생시킬 수 없다

    }

    /**
     * throws RuntimeException는 안된다 오버라이드 메서드가 unchecked 예외를 던지면 오버라이딩 메서드에서는 checked 예외를 던질 수 없다
     * 그 반대는 가능하다
     */
    @Override
    void uncheckedExceptionMethod() throws RuntimeException { // 확인되지 않은 예외는 오버라이드 메서드보다 상위 예외를 발생시킬 수 있다

    }
}
