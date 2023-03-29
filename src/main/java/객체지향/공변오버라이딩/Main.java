package 객체지향.공변오버라이딩;

import java.util.List;

public class Main {
    public static void main(String[] args){
        Parent parent = new Parent("부모");
        Child child = new Child("자식");

        Parent parent1 = parent.getObject();
        Child child1 = child.getObject();

        List<Parent> parents = List.of(parent1, child1);

        for(Parent p : parents){
            p.print();
        }
    }
}
