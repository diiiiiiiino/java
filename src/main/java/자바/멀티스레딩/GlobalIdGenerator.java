package main.java.자바.멀티스레딩;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public final class GlobalIdGenerator implements IdGenerator<String>{
    private static String serverName = "SERVER01";
    private AtomicInteger atomicId = new AtomicInteger();
    private String format;

    public GlobalIdGenerator(String format) {
        this.format = format;
    }

    @Override
    public String getId() {
        int id = atomicId.getAndIncrement();
        return format.format(format, serverName, getDate(), id);
    }

    private String getDate(){
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(yyyyMMdd);
    }
}
