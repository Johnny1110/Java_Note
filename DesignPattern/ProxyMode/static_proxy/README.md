# 靜態代理模式

<br>

-------------------------------

<br>

## 說明

靜態代理可以用生活化的例子解釋，假如有一個麵包師傅，他想要麥麵包給客人，但是他只會做麵包並不會跟客人打交道甚至記帳開發票 ...... 這個時候就需要一位代理出現幫助麵包師傅處理非做麵包專業以外的事情，好讓麵包師傅可以專注在提升麵包品質的工作。

代理模式在程式設計中起到什麼作用呢？像是麵包師傅把非自己專業的工作交給代理人去做就實現了關注點分離，麵包師傅一心一意的投入在做麵包工作上，一旦需要處理麵包生產問題麵包師傅總能很輕鬆的瞭解自己工作內容需要如何調整，因為這就是他的專業，如果今天沒有代理幫助他而是由麵包師傅一手包辦生產加銷售。那出問題了麵包師傅不僅要考量是麵包製作環節的問題還是他跟客人溝通有問題甚至是帳沒算對……

這邊就用麵包師傅的例子來實作一下靜態代理模式。

<br>


## 實作

### 麵包師傅一手包辦全部

* Baker.java

        public class Baker {
            private void gretting() {
                System.out.println("您好，歡迎光臨！");
            }

            private void accounting() {
                System.out.println("找錢跟開發票！");
            }

            public String sellCroissant(){
                gretting();
                String bread = "可頌麵包";
                accounting();
                return bread;

            }

            public String sellTaiwanLonganWithRedWineBread(){
                gretting();
                String bread = "酒釀桂圓麵包 XD..";
                accounting();
                return bread;
            }

            public String sellCustardBread(){
                gretting();
                String bread = "克林姆麵包";
                accounting();
                return bread;
            }
        }

    可以看到，麵包師傅除了要做麵包之外還要招呼客人跟結賬印發票，這似乎不是一個麵包師傅應該要做的事。所以來設計一個代理來幫他吧！

<br>
<br>

### 代理出現了

首先要有一個介面 :

* Baker.java

        public interface Baker {

            String sellCroissant();

            String sellTaiwanLonganWithRedWineBread();

            String sellCustardBread();

        }

真正的麵包師傅 :

* BakerImpl.java

        public class BakerImpl implements Baker {

            public String sellCroissant(){
                String bread = "可頌麵包";
                return bread;
            }

            public String sellTaiwanLonganWithRedWineBread(){
                String bread = "酒釀桂圓麵包 XD..";
                return bread;
            }

            public String sellCustardBread(){
                String bread = "克林姆麵包";
                return bread;
            }
            
        }

代理麵包師傅（銷售員）:

* BakerProxy.java

        public class BakerProxy implements Baker {

            private Baker baker; // 真正麵包師

            public BakerProxy(Baker baker){
                this.baker = baker;
            }

            private void gretting() {
                System.out.println("您好，歡迎光臨！");
            }

            private void accounting() {
                System.out.println("找錢跟開發票！");
            }

            public String sellCroissant() {
                gretting();
                accounting();
                return baker.sellCroissant();
            }

            public String sellTaiwanLonganWithRedWineBread() {
                gretting();
                accounting();
                return baker.sellTaiwanLonganWithRedWineBread();
            }

            public String sellCustardBread() {
                gretting();
                accounting();
                return baker.sellCustardBread();
            }
        }

主程式 ：

Main.java

        public class Main {
            public static void main(String[] args) {
                Baker baker = new BakerProxy(new BakerImpl());
                baker.sellCustardBread();
                baker.sellTaiwanLonganWithRedWineBread();
                baker.sellCroissant();
            }
        }

<br>

### 說明

BakerImpl 與 BakerProxy 同樣實作 Baker 介面，BakerImpl 的實作方法全神貫注在麵包製作上， BakerProxy 有一個建構函式，需要引入一個真正的麵包師傅，當 BakerProxy 被呼叫方法時，他負責處理問候語跟結賬印發票，真正生產麵包的工作則轉給麵包師完成。實現關注點分離。

如此一來，麵包師終於可以專注在製作麵包上拉，可喜可賀！

<br>

## 文件

[Baker.java](./Baker.java)

[BakerImpl.java](./BakerImpl.java)

[BakerProxy.java](./BakerProxy.java)

[Main.java](./Main.java)
