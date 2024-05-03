package 객체지향.구성;

public class Car {
    private final String name;
    private final Engine engine;

    public Car(String name) {
        this.name = name;
        this.engine = new Engine("petrol", 300);
    }

    public String getName() {
        return name;
    }

    public int getHorsepower() {
        return engine.getHorsepower();
    }
}
