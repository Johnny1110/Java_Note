# 靜態工廠方法代替 constructor

<br>

---

<br>

首先，靜態工廠方法出現在很多我們日常使用的 Java API 上，只是我們或許不知道它使用這種設計方法。

舉例：

```java
Date d = Date.from(instance);
```

```java
Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
```

```java
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
```

```java
FileStore fs = Files.getFileStore(path);
```

```java
List<Complaint> litany = Collections.list(legacyLitany);
```

<br>

為甚麼建立物件不好好使用 constructor 就好，而是要用這種所謂 "靜態工廠方法" 呢 ?

<br>

### 使用靜態工廠方法優勢：

<br>

1. 它有自己的名稱。

    `Collections.list()`，`Files.getFileStore()`，`BigInteger.valueOf()`，他們都有各自的名字，比單純 constructor 更加能描述返回的物件性質（易讀性強）。

<br>

2. 不用每次調用時都創建新物件。

    可以先將預先建構好物件，或將建構好的物件緩存起來，重複利用。`Boolean.valueOf()` 說明了這項優勢

    ```java
    public static Boolean.valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
    ```

    如果創建物件代價高，那極推薦使用這項技術。

<br>

3. 可以返回 "原返回類別" 的 "子類別"。

    有點饒口，但是道理很簡單，直接看例子說明：

    ```java
    Pizza obj1 = Pizza.bigPizza("order by A");
    Pizza obj2 = Pizza.littlePizza("order by B");
    ```

    `bigPizza()` 與 `littlePizza()`都是回傳 `Pizza` 的子類。事實上，在 JDK 1.8 後出現的 `java.util.Collections` 介面就有 45 個工具實現類，分別提供不可修改 Sets，同步 Set，List 等等。這些工具類都實作了 `java.util.Collections` 介面，所以我們可以透過它自由取用這些工具類。

    <br>

4. 返回的物件可以根據調用時的參數變化而變化。

    `EnumSet<E> of(E first, E... rest)` 是一個標準的靜態工廠方法，它返回的子類一共有兩種，如果輸入的 `E` 數量 <= 64 個，回傳 `RegalarEumSet`，不然就回傳 `JumboEnumSet`。


<br>
<br>
