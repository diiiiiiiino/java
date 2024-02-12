package 디자인패턴.데코레이터패턴;

public class Main {
    public static void main(String[] args) {
        Americano americano = new Americano();
        Mocha mocha = new Mocha(americano);

        System.out.println("설명 : " + americano.getDescription() + ", cost : " + americano.cost());
        System.out.println("설명 : " + mocha.getDescription() + ", cost : " + mocha.cost());
    }
}
