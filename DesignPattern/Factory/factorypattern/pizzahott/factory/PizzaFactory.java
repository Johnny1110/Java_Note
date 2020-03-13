package factorypattern.pizzahott.factory;

import factorypattern.pizzahott.product.Pizza;

public interface PizzaFactory {
    Pizza createPizza(String type);
}
