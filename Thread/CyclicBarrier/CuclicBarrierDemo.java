public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10); // 限額到10個執行緒就放行

        IntStream.range(0,15).forEach(i -> {
            new Thread(newTask(barrier)).start();
        });
    }

    private static Runnable newTask(CyclicBarrier barrier){
        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is awaiting..");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " is gone..");
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

    }
}