package dino.디자인패턴.옵저버패턴;

public class Main {
    public static void main(String[] args) {
        MediaCompany mediaCompany = new MediaCompany("경기일보");
        User user = new User("홍길동", mediaCompany);

        user.showNews();
        mediaCompany.updateNews("속보 발생!!!!");
        user.showNews();

        mediaCompany.removeObserver(user);
        System.out.println("----------------옵저버 삭제----------------");

        mediaCompany.updateNews("17호 태풍 발생!!!!");
        user.showNews();
    }
}
