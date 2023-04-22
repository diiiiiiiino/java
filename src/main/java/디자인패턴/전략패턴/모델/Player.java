package 디자인패턴.전략패턴.모델;

import 디자인패턴.전략패턴.인터페이스.TerranStrategy;

public class Player {
    final private TerranStrategy terranStrategy;

    public Player(TerranStrategy terranStrategy){
        this.terranStrategy = terranStrategy;
    }

    public void run(){
        this.terranStrategy.run();
    }
}
