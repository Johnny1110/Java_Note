package Decorator.decorator;

import Decorator.base.Beverage;

public class Bubble extends Ingredients {
    public Bubble(Beverage beverage){
        this.beverage = beverage;
        this.price = 5f;
        this.name = "珍珠";
        this.description = "古早味粉圓";
    }


}
