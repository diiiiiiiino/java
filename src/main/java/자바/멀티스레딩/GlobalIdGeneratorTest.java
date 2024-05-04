package main.java.자바.멀티스레딩;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GlobalIdGeneratorTest {
    private final static String format = "%s-%s-%d";
    private GlobalIdGenerator globalIdGenerator;

    @BeforeEach
    void init() {
        globalIdGenerator = new GlobalIdGenerator(format);
    }

    @Test
    @DisplayName("ID 형식 확인")
    void idGenerate(){
        Assertions.assertEquals("SERVER01-1", globalIdGenerator.getId());
    }

    @Test
    @DisplayName("멀티 스레드 동시 처리 ID 생성 확인")
    void whenMultiThreadThenIdGenerate(){
        final int threadCount = 100;
        Thread[] threads = new Thread[threadCount];

        for(int i = 0; i < threadCount; i++){
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 2000; j++){
                    globalIdGenerator.getId();
                }
            });

            threads[i].start();
        }

        for(int i = 0; i < threadCount; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assertions.assertEquals("SERVER01-20240504-200000", globalIdGenerator.getId());
    }
}
