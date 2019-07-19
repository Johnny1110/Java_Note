# Fork & Join 實作費氏數

<br>

---------------------------------------

<br>

## 概要

Fork & Join 框架，適合用來做遞歸運算。把大的任務切成小分分發給不同執行緒處理，最終合併運算結果。

所有套用 Fork & Join 框架的程式必須繼承 ForkJoinTask 或者其子類別，並實作 compute( )。

    public abstract class ForkJoinTask<V> implements Future<V>, Serializable {

        protected abstract V compute();

    }

關於切割任務、運算以及遞歸方法全部定義在 compute( ) 中，按照一定的格式進行。

具體可以如下 ：

    @Override
        protected Integer compute() {
            if (任務太小) {
                處理運算並回傳;
            } else {
                切割任務 task_1 準備遞歸;
                切割任務 task_2 準備遞歸;
                task_1.fork(); // task_1 分給另一個執行緒執行。
                return task_2.compute() + task_1.join(); // task_2運算結果加上 task_1運算結果回傳。
            }
        }

<br>

## 文件

1. [Main.java](./Main.java)
2. [FibonacciTask.java](./FibonacciTask.java)

<br>

## 說明

* Main.java 中使用的 ForkJoinPool 是 Fork & Join 框架專用的 Thread Pool。使用 invoke( ) 載入 task 執行。

        public static void main(String[] args) {
                FibonacciTask task = new FibonacciTask(4);
                ForkJoinPool pool = new ForkJoinPool();
                int result = pool.invoke(task);
                System.out.println(result);
            }

* FibonacciTask 實作 ForkJoinTask 子類別 RecursiveTask，定義費氏數計算方法在 compute( ) 中。

        import java.util.concurrent.RecursiveTask;

        public class FibonacciTask extends RecursiveTask<Integer> {

            private int num;

            public FibonacciTask(int num) {
                this.num = num;
            }

            @Override
            protected Integer compute() {
                if (num <= 1) {
                    return num;
                } else {
                    FibonacciTask f1 = new FibonacciTask(num - 1);
                    FibonacciTask f2 = new FibonacciTask(num - 2);
                    f1.fork();
                    return f2.compute() + f1.join();
                }
            }
        }