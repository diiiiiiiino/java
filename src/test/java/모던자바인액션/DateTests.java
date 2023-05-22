package 모던자바인액션;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class DateTests {

    @DisplayName("12.1.1 LocalDate")
    @Test
    void test12_1_1() {
        LocalDate date = LocalDate.of(2023, 04, 19);

        Assertions.assertEquals(2023, date.getYear());
        Assertions.assertEquals(Month.APRIL, date.getMonth());
        Assertions.assertEquals(4, date.getMonthValue());
        Assertions.assertEquals(19, date.getDayOfMonth());
        Assertions.assertEquals(DayOfWeek.WEDNESDAY, date.getDayOfWeek());
        Assertions.assertEquals(30, date.lengthOfMonth());
        Assertions.assertEquals(false, date.isLeapYear());

        Assertions.assertEquals(2023, date.get(ChronoField.YEAR));
        Assertions.assertEquals(4, date.get(ChronoField.MONTH_OF_YEAR));
        Assertions.assertEquals(19, date.get(ChronoField.DAY_OF_MONTH));

        LocalTime time = LocalTime.of(13, 45, 20);

        Assertions.assertEquals(13, time.getHour());
        Assertions.assertEquals(45, time.getMinute());
        Assertions.assertEquals(20, time.getSecond());

        LocalDate date2 = LocalDate.parse("2023-04-19");
        LocalTime time2 = LocalTime.parse("13:45:20");

        Assertions.assertEquals(date, date2);
        Assertions.assertEquals(time, time2);
    }

    @DisplayName("12.1.2 날짜와 시간 조합")
    @Test
    void test12_1_2() {
        LocalDate date = LocalDate.parse("2017-09-21");
        LocalTime time = LocalTime.parse("13:45:20");

        LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 35, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
    }

    @DisplayName("12.1.3 Instant 클래스 : 기계의 날짜와 시간")
    @Test
    void test12_1_3() {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);

        Assertions.assertThrows(UnsupportedTemporalTypeException.class, () -> Instant.now().get(ChronoField.DAY_OF_MONTH));
    }

    @DisplayName("12.1.4 Duration과 Period 정의")
    @Test
    void test12_1_4() {
        Duration d1 = Duration.between(LocalTime.now(), LocalTime.now());
        Duration d2 = Duration.between(Instant.now(), Instant.now());

        Period tenDays = Period.between(LocalDate.of(2017, 9, 11), LocalDate.of(2017, 9, 21));

        Assertions.assertThrows(DateTimeException.class, () -> Duration.between(Instant.now(), LocalTime.now()));
        Assertions.assertEquals(10, tenDays.getDays());

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays2 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    @DisplayName("12.2 날짜 조정, 파싱, 포매팅")
    @Test
    void test12_2() {
        //절대적인 방식으로 속성 바꾸기 새로운 객체를 반환
        LocalDate date1 = LocalDate.of(2017, 9, 21);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2);

        //상대적인 방식으로 속성 바꾸기 새로운 객체를 반환
        LocalDate date11 = LocalDate.of(2017, 9, 21);
        LocalDate date22 = date11.plusWeeks(1);
        LocalDate date33 = date22.minusYears(6);
        LocalDate date44 = date33.plus(6, ChronoUnit.MONTHS);
    }

    @DisplayName("12.2.1 TemporalAdjusters 사용하기")
    @Test
    void test12_2_1() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }

    @DisplayName("12.2.2 날짜와 시간 객체 출력과 파싱")
    @Test
    void test12_2_2() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(s1);
        System.out.println(s2);

        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(date1);
        System.out.println(date2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date3 = LocalDate.of(2014, 3, 18);
        String formattedDate = date3.format(formatter);
        LocalDate date4 = LocalDate.parse(formattedDate, formatter);

        System.out.println(formattedDate);
        System.out.println(date4);

        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date5 = LocalDate.of(2014, 3, 18);
        String formattedDate2 = date5.format(italianFormatter);
        LocalDate date6 = LocalDate.parse(formattedDate2, italianFormatter);

        System.out.println(formattedDate2);
        System.out.println(date6);

        DateTimeFormatter italianFormatter2 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(". ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        String formattedDate3 = date5.format(italianFormatter2);
        System.out.println(formattedDate3);
    }

    @DisplayName("12.3.1 시간대 사용하기")
    @Test
    void test12_3_1() {
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        Assertions.assertEquals("Europe/Rome", romeZone.getId());
        Assertions.assertEquals("Asia/Seoul", zoneId.getId());

        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println(zdt1);

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println(zdt3);

        Instant instant1 = Instant.now();
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant1, romeZone);
        System.out.println(timeFromInstant);
    }

    @DisplayName("12.3.2 UTC/Greenwich 기준의 고정 오프셋")
    @Test
    void test12_3_2() {
        /**
         * 서버 타임을 제대로 처리할 수 없으므로 권장하지 않는 방식이다.
         * */
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");

        LocalDateTime dateTime = LocalDateTime.of(2023, Month.APRIL, 19, 16, 43);
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);

        System.out.println(dateTimeInNewYork);
    }
}
