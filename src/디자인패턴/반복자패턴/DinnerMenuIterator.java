package 디자인패턴.반복자패턴;

import java.util.Iterator;

public class DinnerMenuIterator implements Iterator<MenuItem> {
    private MenuItem[] menus;
    private int index;

    public DinnerMenuIterator(MenuItem[] menus) {
        this.menus = menus;
    }

    @Override
    public MenuItem next() {
        return menus[index++];
    }

    @Override
    public boolean hasNext() {
        if(index >= menus.length || menus[index] == null){
            return false;
        } else {
            return true;
        }
    }
}
