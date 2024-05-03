package main.java.디자인패턴.반복자패턴;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DinnerMenu dinnerMenu = new DinnerMenu();
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();

        Waitress waitress = new Waitress(List.of(pancakeHouseMenu, dinnerMenu));
        waitress.printMenu();
    }
}
