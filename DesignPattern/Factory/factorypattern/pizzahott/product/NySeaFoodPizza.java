package factorypattern.pizzahott.product;

public class NySeaFoodPizza extends Pizza {
    NySeaFoodPizza(){
        this.pizzaName = "紐約起司披薩";
    }

    @Override
    public void cut(){
        System.out.println("切成方塊狀..");
    }

}
