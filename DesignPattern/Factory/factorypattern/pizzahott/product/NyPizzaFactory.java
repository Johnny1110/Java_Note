package factorypattern.pizzahott.product;

import factorypattern.pizzahott.factory.PizzaFactory;

public class NyPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("起司")){
            return new NyCheesePizza();
        }
        if (type.equals("海鮮")){
            return new NySeaFoodPizza();
        }
        return new NyClassicPizza();
    }
}
