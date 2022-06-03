# 遇到多個 constructor 參數時考慮使用 Builder 設計

<br>

---

<br>

有一種情況，需要用到重疊構造器模式（telescoping constructor），在此模式下只需要提供第一個構造器含有必要參數，剩下構造器都是必要參數 + 可選參數。這樣說有點讓人難懂，直接看範例。

假設我們需要用一個類別表示食品的標示與營養成分，其中必要的資料有：每份的含量、卡路里，剩下有次要資料可有可無，例如：脂肪量、飽和脂肪、膽固醇、鈉等等。

作為一個 Java 工程師，你或許會直覺的像下面這樣設計：

<br>

```java
public class NutritionFacts {
    private final int servingSize;  // (ml)             required
    private final int serving;      // (per container)  required

    private final int calories;     // (per serving)    optional
    private final int fat;          // (g/serving)      optional
    private final int sodium;       // (mg/serving)     optional
    private final int carbohydrate; // (g/serving)      optional
    
    public NutritionFacts(int servingSize, int serving) {
        this(servingSize, serving, 0);
    }

    public NutritionFacts(int servingSize, int serving, int calories) {
        this(servingSize, serving, calories, 0);
    }

    public NutritionFacts(int servingSize, int serving, int calories, int fat) {
        this(servingSize, serving, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int serving, int calories, int fat, int sodium) {
        this(servingSize, serving, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int serving, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.serving = serving;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
```

<br>

可行的一種做法，但是有更多參數時要加入進來時，類別會變得難以維護及閱讀。

<br>

或許 JavaBeans 模式也不錯：

<br>

```java
public class NutritionFacts {
    private final int servingSize;  // (ml)             required
    private final int serving;      // (per container)  required

    private int calories;     // (per serving)    optional
    private int fat;          // (g/serving)      optional
    private int sodium;       // (mg/serving)     optional
    private int carbohydrate; // (g/serving)      optional

    public NutritionFacts(int servingSize, int serving) {
        this.servingSize = servingSize;
        this.serving = serving;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
```

<br>

這種方法不錯，但也有缺陷，因為構造過程被分成幾個 `setter()` 方法中，構造過程中 JavaBeans 可能處於不一致狀態。同時 JavaBeans 模式使得把類別做成不可變的可能性不復存在了。

<br>

還有一種方案，最適合目前遇到的問題。使用 `Builder` 模式。

<br>

```java
public class NutritionFacts {
    
    private final int servingSize;  // (ml)             required
    private final int serving;      // (per container)  required
    private final int calories;     // (per serving)    optional
    private final int fat;          // (g/serving)      optional
    private final int sodium;       // (mg/serving)     optional
    private final int carbohydrate; // (g/serving)      optional

    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.serving = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int servingSize, int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }
        
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
}
```

<br>

使用方法：

<br>

```java
public static void main(String[] args) {
        NutritionFacts facts = new Builder(1000, 5)
                .calories(149)
                .fat(14)
                .sodium(11)
                .carbohydrate(200)
                .build();
    }
```

<br>

