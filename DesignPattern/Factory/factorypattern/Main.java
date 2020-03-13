package factorypattern;

import factorypattern.pizzahott.PizzaStore;
import factorypattern.pizzahott.product.LaPizzaFactory;
import factorypattern.pizzahott.product.NyPizzaFactory;

public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore = new PizzaStore(new NyPizzaFactory());
        nyStore.orderPizza("海鮮");

        PizzaStore laStore = new PizzaStore(new LaPizzaFactory());
        laStore.orderPizza("海鮮");


    }
}

