package 자바.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student implements Comparable<Student> {
    private int id;

    @Override
    public int compareTo(Student student) {
        return student.id - this.id;
    }
}
