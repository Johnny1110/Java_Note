package Decorator.base;

public class Coffee extends Beverage {
    public Coffee(){
        name = "咖啡";
        price = 55f;
        description = "頂級古巴咖啡豆研磨";
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
