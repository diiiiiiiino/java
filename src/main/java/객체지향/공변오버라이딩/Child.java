package 객체지향.공변오버라이딩;

public class Child extends Parent{
    public Child(String name) {
        super(name);
    }
    @Override
    public Child getObject() { //공변 오버라이딩
        return this;
    }

    @Override
    public void print() {
        System.out.println("child!!");
    }
}
