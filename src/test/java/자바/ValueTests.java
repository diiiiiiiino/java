package 자바;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValueTests {

    @DisplayName("")
    @Test
    void test1() {
        Long id = 1L;
        int id2 = 1;
        long longId = 1L;

        Assertions.assertTrue(id == id2 );
        Assertions.assertTrue(id == 1 );
        Assertions.assertTrue(id == 1L );
        Assertions.assertTrue(id == longId);

        Assertions.assertFalse(id.equals(id2));
        Assertions.assertFalse(id.equals(1));
        Assertions.assertTrue(id.equals(1L));
        Assertions.assertTrue(id.equals(longId));

    }
}
