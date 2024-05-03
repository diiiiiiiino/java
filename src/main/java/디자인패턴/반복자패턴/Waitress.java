package main.java.디자인패턴.반복자패턴;

import java.util.Iterator;
import java.util.List;

public class Waitress {
    List<Menu> menus;

    public Waitress(List<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu(){
        for(Menu menu : menus){
            printMenu(menu.createIterator());
        }
    }

    private void printMenu(Iterator<MenuItem> iterator){
        while(iterator.hasNext()){
            MenuItem menuItem = iterator.next();

            System.out.println("Name : " + menuItem.getName() +
                    ", Description : " + menuItem.getDescription() +
                    ", Price : " + menuItem.getPrice());
        }
    }
}
