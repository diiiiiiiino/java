package 디자인패턴.옵저버패턴;

import java.util.ArrayList;
import java.util.List;

public class MediaCompany implements Subject {
    private String name;
    private String news;
    private List<Observer> observers = new ArrayList<>();

    public MediaCompany(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNews() {
        return news;
    }

    public void updateNews(String news){
        this.news = news;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update();
        }
    }
}
