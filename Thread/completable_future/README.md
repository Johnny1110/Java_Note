# 最新的 Async 做法： CompletableFuture

<br>

java 1.8 提供的 CompletableFuture 實現對 FutureTask 的封裝，使其功能更加強大且易用。

以下分幾個 topic 介紹說明：

## AsyncTask



### Async 執行：

* `runAsync()` 執行任務，__不返回值__。
* `supplyAsync()` 執行任務，__並返回值__。

<br>

### 返回值：

* `get()` 等待方法完成，返回結果。錯誤處理：
   * ancellationException – if this future was cancelled
   * ExecutionException – if this future completed exceptionally
   * InterruptedException – if the current thread was interrupted while waiting
  
<br>
  
* `join()` 等待方法完成，返回結果。錯誤處理：
  * CancellationException – if the computation was cancelled
  * CompletionException – if this future completed exceptionally or a completion computation threw an exception

<br>

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    
    public static void main(String[] args) {
        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            System.out.println("do async task, and no return value.");
        });

        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            return "do async task, and return this string.";
        });

        System.out.println(future2.join());
    }
}
```

<br>
<br>

### 組合處理：

* `anyOf()` 返回執行完成最快的那個 Future (但是最快的如果噴錯就大家一起 G)。
* `allOf()` 全部並行執行，如需獲得返回值，需要配合 `thenApply()` 使用。一個任務噴錯不會影響其他。

<br>

anyOf(): 實際場景 - 多備用線路
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest2 {

    public static void main(String[] args) {
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            return "future1: do async task, and return this string.";
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            return "return: do async task, and return this string.";
        });
        // 取執行速度最快的那一個
        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future1, future2);
        System.out.println(anyFuture.join());
    }
}
```

allOf(): 實際場景 - user 註冊資料檢核，all complete then createNewAccount()
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest3 {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "future1: do async task, and return this string.";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "return: do async task, and return this string.";
        });
        CompletableFuture<String> allFutures = CompletableFuture.allOf(future1, future2).thenApply(res -> {
            return future1.join() + future2.join();
        });
        System.out.println(allFutures.join());
    }
}
```

<br>
<br>

### 鏈式處理（無法傳遞 Exception）：

* `thenRun(Runnable runnable)` 不能傳入參數，也不能返回結果，只保證鏈式執行順序。
* `thenAccept(Consumer consumer)` 可傳入參數。
* `thenApply(Function function)` 可傳入參數，並返回值。

<br>

> Tips: 以上的鏈式處理只要過程發生錯誤，就會被中斷。且後面的 task 感知不到前面發生的錯誤。

<br>

thenRun():

```java
public class CompletableFutureTest4 {

  public static void main(String[] args) {
    CompletableFuture future = CompletableFuture.supplyAsync(() -> {
      System.out.println("doing first job.");
      return 1;
    }).thenRun(() -> {
      try {
        Thread.sleep(1000); // to proof not block main thread.
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("doing second job.");
    }).thenRun(() -> {
      System.out.println("doing third job.");
    });

    System.out.println("Main Thread.");
    future.join();
  }
}
```
<br>

thenAccept(Consumer consumer):

```java
public class CompletableFutureTest5 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).thenAccept(val -> {
            System.out.println("doing second job.");
            System.out.println("get val from last task: " + val);
        }).thenAccept(val -> {
            System.out.println("doing third job.");
            System.out.println("get val from last task: " + val);
        });
    }
}
```

result:

```bash
doing first job.
doing second job.
get val from last task: 15
doing third job.
get val from last task: null # 注意這邊，第二個 task 無法傳遞 val 給下一個。
```

<br>

thenAccept(Consumer consumer):

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest6 {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).thenApply(val -> {
            System.out.println("doing second job.");
            System.out.println("get val from last task: " + val);
            return val + 1;
        }).thenApply(val -> {
            System.out.println("doing third job.");
            System.out.println("get val from last task: " + val);
            return val + 1;
        });
        System.out.println("result: " + future.join());
    }
}
```

result:

```bash
doing first job.
doing second job.
get val from last task: 15
doing third job.
get val from last task: 16
result: 17
```

<br>
<br>

### 鏈式處理（可以傳遞 Exception）：

* `whenComplete()` 比照 `thenAccept()`
* `handle()` 比照 `thenApply()`

<br>

whenComplete():

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest7 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).whenComplete((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing second job, throw a ex.");
            throw new RuntimeException("this is an error");
        }).whenComplete((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing third job.");
        });
    }
}
```

result:
```bash
doing first job.
---------------------------------------
get val from last task: 15
get error from last task: null
doing second job, throw a ex. # 拋出一個錯誤
---------------------------------------
get val from last task: null # 上一個 whenComplete() 是不會傳遞值下來的
get error from last task: java.util.concurrent.CompletionException: java.lang.RuntimeException: this is an error # 收到上一個 task 錯誤
doing third job.
```

<br>

handle():

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest8 {

    public static void main(String[] args) {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).handle((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing second job, throw a ex.");
            throw new RuntimeException("this is an error");
        }).handle((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing third job.");
            return 100;
        });
        System.out.println("----------------------------------");
        System.out.println("final result:" + future.join());
    }
}
```

result:

```bash
doing first job.
---------------------------------------
get val from last task: 15
get error from last task: null
doing second job, throw a ex.
---------------------------------------
get val from last task: null
get error from last task: java.util.concurrent.CompletionException: java.lang.RuntimeException: this is an error
doing third job.
----------------------------------
final result:100
```
