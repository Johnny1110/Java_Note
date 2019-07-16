# Thread sleep() 對象鎖 wait() notifyAll()

<br>

--------------------------------------

<br>

## 說明

讀了大半天執行緒回過頭來做一個小實驗的時候發現犯了兩個個非常低及的錯誤。第一個錯誤是誤把 Thread.sleep( ) 當成是釋放鎖的一個方法，第二個是把 wait( ) notify( ) notifyAll( ) 當成跟 Thread.sleep( ) 一樣用。


<br>

## 問題檢討

### 1. Thread.sleep( ) 不釋放鎖

直接看 code 吧 :

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

### 2. wait( ) ?

直接看錯的  code 吧 ：

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

來檢討一下問題吧。首先先說明我這樣寫這段 code 的想法。我認為 wait( ) 跟 sleep( ) 道理一樣，只差在釋不釋放鎖而以，然而觀念其實差多了。

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


問題以修正。