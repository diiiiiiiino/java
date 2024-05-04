package dino.디자인패턴.템플릿메서드패턴;

public class Main {
    public static void main(String[] args) {
        DinnerManner koreaDinnerManner = new KoreaDinnerManner();
        DinnerManner usaDinnerManner = new USADinnerManner();

        koreaDinnerManner.startDinner();
        System.out.println("========================================");
        usaDinnerManner.startDinner();
    }
}
