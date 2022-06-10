# Builder 也可以繼承，比較複雜一點的美妙設計

<br>

--------------------------------

<br>

Builder 也可以繼承，結構超複雜一點，但很美妙。

以 Pizza 為例，Pizza 是一個抽象的定義，具體可以有 NyPizza 等等，每一種 Pizza 都有配料可以選，所以配料（Topping）就定義在 Pizza 這個抽象類，到具體的 NyPizza 我們則讓使用者可以選擇尺吋。

<br>

以下為實作：

<br>

### Pizza

```java
public abstract class Pizza {

    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {

        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        protected abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
```

<br>
<br>

### NyPizza

```java
public class NyPizza extends Pizza {

    public enum Size { SMALL, MEDIUM, LARGE }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self(){
            return this;
        }
    }

    NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
```

<br><br>

使用
```java
public class PizzaOrder {

    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(NyPizza.Size.LARGE)
                .addTopping(Pizza.Topping.HAM)
                .addTopping(Pizza.Topping.MUSHROOM)
                .addTopping(Pizza.Topping.ONION)
                .build();
    }
}
```