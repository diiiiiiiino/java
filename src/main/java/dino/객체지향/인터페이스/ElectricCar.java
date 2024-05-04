package dino.객체지향.인터페이스;

public class ElectricCar implements Vehicle{
    private String name;
    private int horsePower;

    @Override
    public void speedUp() {
        System.out.println("Speed up the electric car ...");
    }

    @Override
    public void slowDown() {
        System.out.println("Slow down the electric car ...");
    }

    @Override
    public double computeConsumption(int fuel, int distance, int horsePower) {
        return Math.random() * 60d / Math.pow(Math.random(), 3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
