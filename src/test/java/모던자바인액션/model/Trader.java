package 모던자바인액션.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trader {
    private final String name;
    private final String city;
}
