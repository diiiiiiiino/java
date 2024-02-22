package 객체지향.공변오버라이딩;

public class Parent{
    String name;

    public Parent(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getObject() {
        return this;
    }

    public void print(){
        System.out.println("parent!!");
    }
}
