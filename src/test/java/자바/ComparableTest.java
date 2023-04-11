package 자바;

import org.junit.jupiter.api.Test;
import 자바.model.Student;

import java.util.List;

public class ComparableTest {

    @Test
    void comparableTest(){
        List<Student> students = List.of(new Student(1), new Student(3), new Student(2), new Student(5));

        students.stream()
                .sorted()
                .forEach(System.out::println);
    }
}
