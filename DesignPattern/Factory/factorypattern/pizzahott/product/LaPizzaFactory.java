package factorypattern.pizzahott.product;

import factorypattern.pizzahott.factory.PizzaFactory;

public class LaPizzaFactory extends Pizza implements PizzaFactory {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("起司")){
            return new LaCheesePizza();
        }
        if (type.equals("海鮮")){
            return new LaSeaFoodPizza();
        }
        return new LaClassicPizza();
    }
}
