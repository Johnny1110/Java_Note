package Decorator.decorator;

import Decorator.base.Beverage;

public class Milk extends Ingredients {
    public Milk(Beverage beverage){
        this.beverage = beverage;
        this.price = 10f;
        this.name = "奶";
        this.description = "高級牛奶";
    }
}
