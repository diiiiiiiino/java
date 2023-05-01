package 디자인패턴.템플릿메서드패턴;

public class Tea extends CaffeineBeverage{

    @Override
    void brew() {
        System.out.println("찻입을 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("설탕과 우유를 추가하는 중");
    }
}
