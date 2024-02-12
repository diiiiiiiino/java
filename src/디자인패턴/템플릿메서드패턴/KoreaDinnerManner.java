package 디자인패턴.템플릿메서드패턴;

public class KoreaDinnerManner extends DinnerManner {

    @Override
    void eat() {
        System.out.println("수저로 식사!");
    }

    @Override
    void prayer() {}
}
