package 모던자바인액션.model;

public class Fruit {
    private int weight;
    private Color color;

    private int price;

    public Fruit(){

    }

    public Fruit(int weight) {
        this.weight = weight;
    }

    public Fruit(Color color) {
        this.color = color;
    }

    public Fruit(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Fruit(int weight, Color color, int price) {
        this.weight = weight;
        this.color = color;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
