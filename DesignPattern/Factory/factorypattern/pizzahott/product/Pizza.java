package factorypattern.pizzahott.product;

public abstract class Pizza {

    protected String pizzaName;

    public void cook(){
        System.out.println(String.format("製做 %s ..", this.pizzaName));
    }

    public void cut(){
        System.out.println("切塊..");
    }

    public void boxing(){
        System.out.println("封裝入盒..");
    }
}


