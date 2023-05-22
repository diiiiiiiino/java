package spring.threadlocal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.threadlocal.code.FieldService;
import spring.threadlocal.code.ThreadLocalService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadLocalServiceTest {
    @Test
    void test() throws InterruptedException {
        FieldService fieldService = new FieldService();

        Thread thread1 = new Thread(() -> fieldService.logic("name1"));
        thread1.setName("thread-1");

        Thread thread2 = new Thread(() -> fieldService.logic("name2"));
        thread1.setName("thread-2");

        thread1.start();
        Thread.sleep(100);
        thread2.start();
        
        Thread.sleep(2000);
        System.out.println("Main 스레드 종료");

        Assertions.assertEquals("name2", fieldService.getName());
    }

    @Test
    void test2() throws InterruptedException {
        ThreadLocalService threadLocalService = new ThreadLocalService();

        Thread thread1 = new Thread(() -> threadLocalService.logic("name1"));
        thread1.setName("thread-1");

        Thread thread2 = new Thread(() -> threadLocalService.logic("name2"));
        thread1.setName("thread-2");

        thread1.start();
        Thread.sleep(100);
        thread2.start();

        Thread.sleep(2000);
        System.out.println("Main 스레드 종료");
    }

    @Test
    void test3() throws InterruptedException {
        ThreadLocalService threadLocalService = new ThreadLocalService();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 1; i <= 5; i++){
            String name = "name" + i;
            executorService.execute(() -> threadLocalService.logic(name));
            Thread.sleep(100);
        }

        Thread.sleep(10000);
        System.out.println("Main 스레드 종료");
    }
}
