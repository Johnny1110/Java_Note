package Decorator.decorator;

import Decorator.base.Beverage;

public class Ingredients extends Beverage {
    protected Beverage beverage;

    @Override
    public float getPrice() {
        return this.price + this.beverage.getPrice();
    }

    @Override
    public String getName() {
        return this.name + this.beverage.getName();
    }
}
