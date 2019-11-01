# Thread 錯誤的鎖對象

<br>

----------------------------------------------------------------
<br>

## 簡介

最近在看一些執行緒的書，看到了一個關於鎖對象的有趣錯誤，這裡紀錄一下。防止之後遇到不知道怎麼解。


<br>

## 問題

*   BadLockInteger.java

        public class BadLockIntegerimplements Runnable {
            private static Integer i = 0;
            private static BadLockInteger task = new BadLockInteger();

            @Override
            public void run() {
                for(int j = 0; j < 100000; j++){
                    synchronized (i){
                        i++;
                    }
                }
            }

            public static void main(String[] args) throws InterruptedException {
                Thread thread1 = new Thread(task);
                Thread thread2 = new Thread(task);
                thread1.start();
                thread2.start();
                thread1.join();
                thread2.join();
                System.out.println("i = " + i);
            }
        }


    輸出:

        i = 145633

    看起來 synchronized (i) 已經對 i 做同步鎖了，應該答案要是 200000 才對啊，為甚麼還是會發生這種執行緒戶興干擾的錯誤呢?


    來看一下 Integer 的本質。在 Java 中的 Integer 屬於 immutable ( 對象一旦建立便不可修改 )。簡單說當宣告一個 Integer = 1，那麼他這輩子就只能是 1。那要是 i++ 呢 ? 事實上，使用 i++ 在底層實作上使用的方式是 :

        i = Integer.valueOf(i.intValue() + 1);

    Integer.valueOf() 的實作 :

        public static Integer valueOf(int i) {
            if (i >= IntegerCache.low && i <= IntegerCache.high)
                return IntegerCache.cache[i + (-IntegerCache.low)];
            return new Integer(i);
        }

    IntegerCache 的預設可快取值除非設定 VM Options，不然就是在 -127 ~ 127 之間。值在預設可快取值區間就回傳快取池中的 Integer。不然就回傳新的 Integer。因此可以知道。此處的 i++ 的本質就是建立一個新的 Integer，然後將新物件的引用給到 i 上。

    這樣一來也就理解為甚麼 synchronized (i) 會失效了， i 實際指向的物件一直都在變，因此每一個 Thread 可能鎖到的都不是同一個物件。



    <br>
    <br>

    修正問題 :

        @Override
        public void run() {
            for(int j = 0; j < 100000; j++){
                synchronized (task)){
                    i++;
                }
            }
        }

    將同步鎖鎖在不變參考的 task 上就可以了。