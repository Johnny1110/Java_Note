import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        FibonacciTask task = new FibonacciTask(4);
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(task);
        System.out.println(result);
    }
}