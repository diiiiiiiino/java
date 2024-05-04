package dino.디자인패턴.반복자패턴;

import java.util.Iterator;

public class DinnerMenu implements Menu{
    private static final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem[] menus;

    public DinnerMenu() {
        this.menus = new MenuItem[MAX_ITEMS];

        addItem("호밀빵", "국내산 호밀 반죽으로 제조", 3500);
        addItem("스프", "국내산 옥수수로 제조", 1500);
        addItem("돈까스", "제주산 흑돼지로 제조", 1500);
    }

    public void addItem(String name, String description, double price){
        MenuItem menuItem = new MenuItem(name, description, price);

        if(numberOfItems >= MAX_ITEMS){
            System.out.println("메뉴를 더이상 추가할 수 없습니다.");
        } else {
            menus[numberOfItems++] = menuItem;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator(){
        return new DinnerMenuIterator(menus);
    }

}
