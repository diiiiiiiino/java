package 객체지향.인터페이스;

public class SteamCar implements Vehicle{
    private String name;

    @Override
    public void speedUp() {
        System.out.println("Speed up the steam car ...");
    }

    @Override
    public void slowDown() {
        System.out.println("Slow down the steam car ...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
