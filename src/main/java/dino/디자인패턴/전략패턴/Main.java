package dino.디자인패턴.전략패턴;

public class Main {
    public static void main(String[] args) {
        Attack punch = new Punch();
        Attack lowKick = new LowKick();

        Player player = new Player(punch);
        player.attack();

        System.out.println("-------------lowKick으로 변경-------------");
        player.setAttack(lowKick);
        player.attack();
    }
}
