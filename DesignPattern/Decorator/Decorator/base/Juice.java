package Decorator.base;

public class Juice extends Beverage {
    public Juice(){
        name = "果汁";
        price = 40f;
        description = "新鮮水果現榨成汁";
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
