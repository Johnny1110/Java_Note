package factorypattern.pizzahott;

import factorypattern.pizzahott.factory.PizzaFactory;
import factorypattern.pizzahott.product.Pizza;

public class PizzaStore {

    protected PizzaFactory pizzaFactory;

    public PizzaStore(PizzaFactory factory){
        this.pizzaFactory = factory;
    }

    public Pizza orderPizza(String type){
        System.out.println("收到訂單");
        Pizza pizza = this.pizzaFactory.createPizza(type);
        pizza.cook();
        pizza.cut();
        pizza.boxing();
        return pizza;
    }
}
