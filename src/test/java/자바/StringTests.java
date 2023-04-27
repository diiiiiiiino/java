package 자바;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTests {

    @DisplayName("")
    @Test
    void test1() {
        Assertions.assertEquals(-1, "AAA".compareTo("AAB"));
        Assertions.assertEquals(0, "AAA".compareTo("AAA"));
    }
}
