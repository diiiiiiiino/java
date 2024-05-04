package dino.디자인패턴.반복자패턴;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PancakeHouseMenu implements Menu{
    List<MenuItem> menus;

    public PancakeHouseMenu() {
        this.menus = new ArrayList<>();

        addItem("팬케이크", "국내산 밀가루 반죽으로 제조", 2000);
        addItem("딸기 팬케이크", "국내산 딸기로 제조", 3000);
        addItem("초코 팬케이크", "허쉬 초콜렛으로 제조", 4500);
    }

    public void addItem(String name, String description, double price){
        MenuItem menuItem = new MenuItem(name, description, price);
        menus.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator(){
        return menus.iterator();
    }

}
