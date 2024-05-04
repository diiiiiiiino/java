package main.java.자바.멀티스레딩;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultiThreadingTest {

    @Test
    @DisplayName("스레드별로 멤버변수가 다른 객체를 참조할 때 동시성 문제 확인")
    public void eachMemberVarRefConcurrency(){
        Item item = new Item();
        Item item2 = new Item();
        MyRunnable myRunnable1 = new MyRunnable(item);
        MyRunnable myRunnable2 = new MyRunnable(item2);

        Thread thread1 = new Thread(myRunnable1);
        Thread thread2 = new Thread(myRunnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(10, item.count);
        Assertions.assertEquals(10, item2.count);
    }

    @Test
    @DisplayName("스레드별로 멤버변수가 같은 객체를 참조할 때 동시성 문제 확인")
    public void sameMemberVarRefConcurrency(){
        Item item = new Item();

        MyRunnable myRunnable1 = new MyRunnable(item);
        MyRunnable myRunnable2 = new MyRunnable(item);

        Thread thread1 = new Thread(myRunnable1);
        Thread thread2 = new Thread(myRunnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertNotEquals(10, item.count);
    }

    private class MyRunnable implements Runnable{
        private Item item;
        public MyRunnable(Item item) {
            this.item = item;
        }

        @Override
        public void run() {
            for(int i = 0; i < 10; i++){
                item.count++;
            }
        }
    }

    private static class Item {
        private int count;
    }
}
