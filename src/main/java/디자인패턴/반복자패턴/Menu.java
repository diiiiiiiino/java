package 디자인패턴.반복자패턴;

import java.util.Iterator;

public interface Menu {
	public Iterator<MenuItem> createIterator();
}
