# CyclicBarrier 執行緒的緩衝

<br>

------------------------------------

<br>

## 說明

學習執行緒過程中發現一個蠻有趣的東西，記錄下來。

CyclicBarrier 屬於 java.util.concurrent 套件，可以做到對執行緒放行的管理。限定執行緒數量到達一定數量才放行。

<br>

## 文件

[CuclicBarrierDemo.java](./CuclicBarrierDemo.java)

<br>

## 詳細

* code

        public static void main(String[] args) {
                CyclicBarrier barrier = new CyclicBarrier(10); // 限額到2個執行緒就放行

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

* output

        Thread-8 is awaiting..
        Thread-9 is awaiting..
        Thread-0 is awaiting..
        Thread-2 is awaiting..
        Thread-7 is awaiting..
        Thread-3 is awaiting..
        Thread-1 is awaiting..
        Thread-5 is awaiting..
        Thread-11 is awaiting..
        Thread-12 is awaiting..
        Thread-12 is gone..
        Thread-14 is awaiting..
        Thread-13 is awaiting..
        Thread-10 is awaiting..
        Thread-6 is awaiting..
        Thread-3 is gone..
        Thread-7 is gone..
        Thread-2 is gone..
        Thread-9 is gone..
        Thread-0 is gone..
        Thread-8 is gone..
        Thread-11 is gone..
        Thread-5 is gone..
        Thread-1 is gone..
        Thread-4 is awaiting..

* 我設定 10 個限制，可以看到第一個 gone 是在 10 個 await 之後出現。一共 16 個執行緒最終 gone 的也只會有 10 個，不會再多了。