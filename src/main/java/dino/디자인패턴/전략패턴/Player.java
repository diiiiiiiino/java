package dino.디자인패턴.전략패턴;

public class Player {
    private Attack attack;

    public Player(Attack attack) {
        this.attack = attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public void attack(){
        this.attack.attack();
    }
}
