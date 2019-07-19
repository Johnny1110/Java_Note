# ExecutorService

<br>

---------------------------------------------------------

<br>

## 說明

ExecutorService 介面是 Thread Pool 的概念，其主要實作分為 2 類，分別爲 :

* Executors.newCachedThreadPool( )
* Executors.newFixedThreadPool(int nThreads);

以下是對比表格

<table>

<tr>
<td> Pool Name </td>
<td>|</td>
<td>數量指定</td>
<td>自動增加數量</td>
<td>生命週期</td>
</tr>

<tr>
<td>CachedThreadPool</td>
<td>|</td>
<td>由系統自動調整</td>
<td>工作量大自動增加</td>
<td>閒置 60 secs 或呼叫 shutdown() 終止</td>
</tr>

<tr>
<td>FixedThreadPool</td>
<td>|</td>
<td>自己指定</td>
<td>固定數量</td>
<td>呼叫 shutdown() 時終止</td>
</tr>

</table>

<br>


ExecutorService 需要搭配 Future<T> 與 Callable 介面使用。ExecutorService 執行 Callable 的結果以 Future<T> 回傳給 Main 進程，再由 Future<T> 的 get( ) 取出。

這裏我有兩個實作，一個是組塞式等待 ExecutorServiceDemo1，這並不是明智的使用方式，Future 一旦啟用 get( ) 方法就會在當前進程上組塞等待，如果一開始直接使用 get( ) 就跟沒用多執行緒一樣。另一個 ExecutorServiceDemo2 則是正常使用方法。

**ExecutorService 的 shutdown( ) 方法被啟動後會等待所有執行緒完成工作之後再統一關閉。**

<br>

## 文件

1. [FutureAndPool1.java](./FutureAndPool1.java)
2. [FutureAndPool2.java](./FutureAndPool2.java)

<br>

## 解釋

* FutureAndPool1.java 中 -注1- 使用 future.get() 直接造成組塞等待，導致主線程後面任務無法執行。

        public static void main(String[] args) throws ExecutionException, InterruptedException {
                ExecutorService service = Executors.newCachedThreadPool();
            //ExecutorService service Executors.newFixedThreadPool(5);
                Callable<String> task = new FutureAndPool().new CallableTask();
                Future<String> future = service.submit(task);

                System.out.println(future.get());  //注 1
                System.out.println("Main Thread do things");
                service.shutdown();
                System.out.println("service shutdown");
            }

    output :

        on call
        finished task
        Wed Jul 17 17:21:36 CST 2019
        Main Thread do things
        service shutdown



<br>

* FutureAndPool2.java 則修正了這個問題，利用 shutdown( ) 的機制 :

        public static void main(String[] args) throws ExecutionException, InterruptedException {
                ExecutorService service = Executors.newCachedThreadPool();
            //ExecutorService service Executors.newFixedThreadPool(5);
                Callable<String> task = new FutureAndPool().new CallableTask();
                Future<String> future = service.submit(task);

                System.out.println("Main Thread do things");
                service.shutdown();
                System.out.println("shutdown already triggered");
                System.out.println(future.get());
            }

    output :

        Main Thread do things
        on call
        shutdown already triggered
        finished task
        Wed Jul 17 17:09:58 CST 2019

    可以看到 shutdown already triggered 在 finished task 前出現，證明雖然啟動了 service.shutdown() 但是還是會等到執行緒真正完成任務後才正式關閉。而且 shutdown 之後或之前都不影響主線程任務執行。