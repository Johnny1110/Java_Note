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