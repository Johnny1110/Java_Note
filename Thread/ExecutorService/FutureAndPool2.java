public class FutureAndPool2 {

    private class CallableTask implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("on call");
            Thread.sleep(5000);
            System.out.println("finished task");
            return new Date().toString();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
      //ExecutorService service Executors.newFixedThreadPool(5);
        Callable<String> task = new FutureAndPool2().new CallableTask();
        Future<String> future = service.submit(task);
        
        System.out.println("Main Thread do things");
        service.shutdown();
        System.out.println("shutdown already triggered");
        System.out.println(future.get());
    }
}