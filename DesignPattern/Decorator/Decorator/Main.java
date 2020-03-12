package Decorator;

import Decorator.base.Beverage;
import Decorator.base.Tea;
import Decorator.decorator.Bubble;
import Decorator.decorator.Milk;

public class Main {
    public static void main(String[] args) {
        Beverage bubbleMilkTea = new Bubble(new Milk(new Tea()));
        System.out.println("名稱 : " + bubbleMilkTea.getName());
        System.out.println("價格 : " + bubbleMilkTea.getPrice());

    }
}
