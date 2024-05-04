package dino.디자인패턴.템플릿메서드패턴;

public abstract class DinnerManner {
    boolean isPlayer;

    public DinnerManner() {
        this.isPlayer = false;
    }

    void startDinner(){
        handWash();
        prepareDish();
        if(isPlayer){
            prayer();
        }
        eat();
        dishWash();
    }

    void handWash(){
        System.out.println("손 씻기!!");
    }

    void prepareDish(){
        System.out.println("접시 준비!!");
    }

    void dishWash(){
        System.out.println("설거지!!");
    }

    abstract void eat();

    /**
     * hook 메서드
     */
    abstract void prayer();

    void setPrayer(boolean isPlayer){
        this.isPlayer = isPlayer;
    }
}
