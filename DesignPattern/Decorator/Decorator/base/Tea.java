package Decorator.base;

public class Tea extends Beverage {

    public Tea(){
        name = "茶";
        price = 25f;
        description = "上好的阿里山高山茶";
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
