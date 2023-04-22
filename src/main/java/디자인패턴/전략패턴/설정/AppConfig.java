package 디자인패턴.전략패턴.설정;

import 디자인패턴.전략패턴.인터페이스.OneFactDouble;
import 디자인패턴.전략패턴.인터페이스.TerranStrategy;

public class AppConfig {

    public TerranStrategy terranStrategy(){
        return new OneFactDouble();
    }
}
