package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

public class StreamParallelTests extends StreamBaseTest {

    @DisplayName("병렬 스트림에서 사용하는 스레드 풀 설정")
    @Test
    void test(){
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

        Assertions.assertEquals(8, processors); //사용중인 노트북의 논리프로세서 갯수가 8개이기때문에 8개로 검증함
    }

    @DisplayName("7.1.3 병렬 스트림의 부작용")
    @Test
    void test2() {
        long n = 10_000_000L;
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        Assertions.assertNotEquals(50000005000000L, accumulator.total);
    }

    public class Accumulator{
        public long total = 0;
        public void add(long value) {
            total += value;
        }
    }
}
