package 디자인패턴.옵저버패턴;

public class User implements Observer {
    private String name;
    private String news;
    private MediaCompany mediaCompany;

    public User(String name, MediaCompany mediaCompany) {
        this.name = name;
        this.mediaCompany = mediaCompany;
        this.mediaCompany.registerObserver(this);
    }

    public void showNews(){
        System.out.println("뉴스 : " + news);
    }

    @Override
    public void update() {
        this.news = mediaCompany.getNews();
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", news=" + news +
                '}';
    }
}
