package Decorator.base;

public abstract class Beverage {
    protected String name;
    protected float price;
    protected String description;

    public abstract float getPrice();

    public abstract String getName();
}
