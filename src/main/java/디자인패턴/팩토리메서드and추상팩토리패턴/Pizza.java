package 디자인패턴.팩토리메서드and추상팩토리패턴;

public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String source;

    protected PizzaIngredientFactory ingredientFactory;

    public Pizza(String name, PizzaIngredientFactory ingredientFactory) {
        this.name = name;
        this.ingredientFactory = ingredientFactory;
    }

    abstract void prepare();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
