package 디자인패턴.템플릿메서드패턴;

public class BeverageTestDrivce {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        tea.prepareRecipe();
        coffee.prepareRecipe();
    }
}
