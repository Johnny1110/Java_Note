# Thread sleep() 對象鎖 wait() notifyAll()

<br>

--------------------------------------

<br>

## 說明

讀了大半天執行緒回過頭來做一個小實驗的時候發現犯了三個個非常低級的錯誤。第一個錯誤是誤把 Thread.sleep( ) 當成是釋放鎖的一個方法，第二個是把 wait( ) notify( ) notifyAll( ) 當成跟 Thread.sleep( ) 一樣用。最後一個是沒注意 Thread.sleep() 是靜態方法。

<br>

1. [Thread.sleep( ) 不釋放鎖](#1)
2. [wait( ) ?](#2)
3. [sleep( ) 的誤區](#3)


<br>

## 問題檢討

<span id="1">

### 1. Thread.sleep( ) 不釋放鎖 

* 直接看 code 吧 :

        public class Test1 {
            public static void main(String[] args) throws InterruptedException {
                Object locker = new Object(); // 鎖

                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        synchronized (locker) {
                            System.out.println("t1 runing");
                            System.out.println("t1 sleep for 5sec");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("t1 is over");
                        }
                    }
                };

                Thread t2 = new Thread(){
                    @Override
                    public void run() {
                        synchronized (locker) {
                            System.out.println("t2 running");
                            System.out.println("t2 is over");
                        }
                    }
                };

                t1.start();
                Thread.sleep(1000);
                t2.start();
            }
        }

    簡單說明就是我一直以爲 t1 sleep 時會釋放 locker ，屆時 t2 可以取得鎖資源並在 t1 sleep 時執行完畢。

    事實上 Thread.sleep( ) 並不會釋放鎖資源。要想釋放鎖資源要使用 Object.wait( )。這也是我發現我犯的第二個錯誤。

<br>
<br>

<span id="2">

### 2. wait( ) ?

* 直接看錯的  code 吧 ：

        public class Test1 {
            public static void main(String[] args) throws InterruptedException {
                Object locker = new Object();

                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        synchronized (locker) {
                            System.out.println("t1 runing");
                            System.out.println("t1 sleep for 5sec");
                            try {
                                wait(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("t1 is over");
                        }
                    }
                };



                Thread t2 = new Thread(){
                    @Override
                    public void run() {
                        synchronized (locker) {
                            System.out.println("t2 running");
                            System.out.println("t2 is over");
                            notifyAll();
                        }
                    }
                };

                t1.start();
                Thread.sleep(1000);
                t2.start();
            }
        }

    來看看 output :

        t1 runing
        t1 sleep for 5sec
        Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
            at java.base/java.lang.Object.wait(Native Method)
            at Test1$1.run(Test1.java:16)
        t2 running
        t2 is over
        Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
            at java.base/java.lang.Object.notifyAll(Native Method)
            at Test1$2.run(Test1.java:34)

    噴錯了，來檢討一下問題吧。首先先說明我這樣寫這段 code 的想法。我認為 wait( ) 跟 sleep( ) 道理一樣，只差在釋不釋放鎖而以，然而觀念其實差多了。

    當執行緒的方法 synchronized 取的物件鎖時，是將自己取得該物件鎖(排他使用權)的資訊放入物件中，其他執行緒發現該物件被佔用就乖乖排隊等待。那麼將鎖資源釋放的權力在誰手上呢？自然不是執行緒拉，執行緒只能把佔有鎖的資訊放入物件鎖中，釋放鎖資源的動作要物件本身來執行，同樣 notify( ) 與 notifyAll( ) 也是一樣的道理。釋放鎖資源跟通知執行緒加入爭奪鎖的動作通通只有鎖本身可以執行。執行緒的權力只有決定要不要睡覺而以。

    所以來修 code 吧：(極其低級錯誤 TAT)

        public class Test1 {
            public static void main(String[] args) throws InterruptedException {
                Object locker = new Object();

                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        synchronized (locker) {
                            System.out.println("t1 runing");
                            System.out.println("t1 sleep for 5sec");
                            try {
                                locker.wait(5000); // 改正

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("t1 is over");
                        }
                    }
                };



                Thread t2 = new Thread(){
                    @Override
                    public void run() {
                        synchronized (locker) {
                            System.out.println("t2 running");
                            System.out.println("t2 is over");
                            locker.notifyAll(); // 改正
                        }
                    }
                };

                t1.start();
                Thread.sleep(1000);
                t2.start();
            }
        }

    output ：

        t1 runing
        t1 sleep for 5sec
        t2 running
        t2 is over
        t1 is over


    問題已修正。

<br>
<br>


<span id="3">

### 3. sleep( ) 的誤區

* 先來看看 code :

        public static void main(String[] args) throws InterruptedException {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        System.out.println("t-1 dead.");
                    }
                };

                Thread t2 = new Thread() {
                    @Override
                    public void run() {
                        System.out.println("t-2 dead.");
                    }
                };

                t1.start();
                t1.sleep(2000);
                t2.start();
                t2.sleep(1000);
                System.out.println("finished.");

            }

    我預期的 output 應該是這樣的 :

        finished.
        t-2 dead.
        t-1 dead.

    但是實際上的 output 是這樣 :

        t-1 dead.
        t-2 dead.
        finished.


    看一下 sleep( ) 的 API :

        public static native void sleep(long millis) throws InterruptedException;

    重點是 sleep( ) 是靜態方法，也就是說這個方法不屬於個體物件，而是屬於 Thread 這個類別的方法，這樣一來首先像 t1.sleep(2000); t2.sleep(1000); 這樣定物件就是沒有意義的，全部改為 Thread.sleep( ) 就好了。

    那 Thread.sleep( ) 的機制是甚麼呢 ? 其實他就只作用於呼叫這個方法的執行緒內。這裡也就是 Main 主進程中。

    換句話說，要想通過 sleep( ) 實現我預期中的 output 應該要這樣實現 :

        public static void main(String[] args) throws InterruptedException {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            System.out.println("t-1 dead.");
                        }
                    }
                };

                Thread t2 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            System.out.println("t-2 dead.");
                        }
                    }
                };

                t1.start();
                t2.start();
                System.out.println("finished.");
            }

