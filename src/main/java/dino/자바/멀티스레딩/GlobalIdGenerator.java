package dino.자바.멀티스레딩;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public final class GlobalIdGenerator implements IdGenerator<String>{
    private static String serverName = "SERVER01";
    private AtomicInteger atomicId = new AtomicInteger();
    private String format;
    private MyDateTimeFormat myDateTimeFormat;


    public GlobalIdGenerator(String format, MyDateTimeFormat myDateTimeFormat) {
        this.format = format;
        this.myDateTimeFormat = myDateTimeFormat;
    }

    @Override
    public String getId() {
        int id = atomicId.getAndIncrement();
        return format.format(format, serverName, myDateTimeFormat.getValue(), id);
    }

    public static class MyDateTimeFormat{
        private DateTimeFormatter dateTimeFormatter;

        public MyDateTimeFormat(DateTimeFormatter dateTimeFormatter) {
            this.dateTimeFormatter = dateTimeFormatter;
        }

        public String getValue() {
            return LocalDate.now().format(dateTimeFormatter);
        }
    }
}
