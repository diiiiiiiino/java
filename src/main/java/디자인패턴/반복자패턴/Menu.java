package main.java.디자인패턴.반복자패턴;

import java.util.Iterator;

public interface Menu {
    Iterator<MenuItem> createIterator();
}
