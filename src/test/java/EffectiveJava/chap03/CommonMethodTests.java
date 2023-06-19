package EffectiveJava.chap03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 모던자바인액션.model.Color;

import java.util.ArrayList;
import java.util.List;

public class CommonMethodTests {

    @DisplayName("equals 대칭성")
    @Test
    void symmetry() {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);

        Assertions.assertFalse(cis.equals(s));
        Assertions.assertFalse(s.equals(cis));
        Assertions.assertFalse(list.contains(s));
    }

    @DisplayName("eqauls 추이성")
    @Test
    void transitivity() {
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        ColorPoint p2 = new ColorPoint(1, 2, Color.RED);
        ColorPoint p3 = new ColorPoint(1, 2, Color.RED);

        Assertions.assertTrue(p1.equals(p2));
        Assertions.assertTrue(p2.equals(p3));
        Assertions.assertTrue(p1.equals(p3));
    }
}
