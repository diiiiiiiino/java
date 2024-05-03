package main.java.디자인패턴.템플릿메서드패턴;

public class USADinnerManner extends DinnerManner {

    public USADinnerManner() {
        this.isPlayer = true;
    }

    @Override
    void eat() {
        System.out.println("포크로 식사!");
    }

    @Override
    void prayer() {
        System.out.println("자유의 여신에게 기도");
    }
}
