package 디자인패턴.전략패턴;

import 디자인패턴.전략패턴.모델.Player;
import 디자인패턴.전략패턴.설정.AppConfig;

public class Main {
    public static void main(String[] args){
        AppConfig appConfig = new AppConfig();

        Player player = new Player(appConfig.terranStrategy());
        player.run();
    }
}
