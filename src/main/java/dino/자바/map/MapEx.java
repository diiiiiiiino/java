package dino.자바.map;

import java.util.HashMap;
import java.util.Map;

public class MapEx {
    public void mapMerge(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.merge(0, 1, Integer::sum);

        map.put(0, null);  //<0, null>
        map.merge(0, 1, (integer, integer2) -> null); //<0, 1>

        map.put(0, 1); //<0, 1>
        map.merge(0, 1, (integer, integer2) -> null); //deleted

        map.merge(0, 1, (integer, integer2) -> null); //<0, 1>

        map.put(0, null);    //<0, null>
        map.merge(0, 1, Integer::sum);    //<0, 1>

        map.put(0, 1);     //<0, 1>
        map.merge(0, 1, Integer::sum);   //<0, 2>

        map.merge(0, 1, Integer::sum); //<0, 1>
    }
}
