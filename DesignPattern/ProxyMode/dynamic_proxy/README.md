# 動態代理模式

<br>

-------------------------------

<br>

## 說明

關於代理就不在綴述了，這邊以另一中方法實作代理模式，使用的是 java.lang.reflect 套件中的 InvocationHandler 搭配 Proxy。 

<br>

## 實作

一樣要先定義介面 ：

* Baker.java

        public interface Baker {

            String sellCroissant();

            String sellTaiwanLonganWithRedWineBread();

            String sellCustardBread();

        }

再來是真正的麵包師實作 :

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

然後是定義 Proxy 工作任務的 InvocationHandler 實作 :

* BakerProxyHandler.java

        import java.lang.reflect.InvocationHandler;
        import java.lang.reflect.Method;
        import java.lang.reflect.Proxy;

        public class BakerProxyHandler implements InvocationHandler {

            private Object target; // target 爲真正的 Baker

            public Object getProxyInstance(Object target){
                this.target = target;
                return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                        target.getClass().getInterfaces(),
                        this);
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                greeting();
                method.invoke(target, args); // target 的方法實際被觸發。
                account();
                return null;
            }

            private void greeting(){
                System.out.println("您好歡迎光臨...");
            }

            private void account(){
                System.out.println("結賬印發票...");
            }
        }

最後是主程式部分 ：

* Main.java

        public class Main {
            public static void main(String[] args) {
                BakerProxyHandler bakerProxyHandler = new BakerProxyHandler();
                Baker baker = (Baker) bakerProxyHandler.getProxyInstance(new BakerImpl());
                baker.sellCroissant();
                baker.sellCustardBread();
                baker.sellTaiwanLonganWithRedWineBread();
            }
        }

<br>

## 說明

這邊主要是 InvocationHandler 搭配 Proxy 有點難以理解。其實想簡單一點。InvocationHandler 定義了一個代理需要執行的動作 （定義在 invoke( ) 方法中）。Proxy 物件的靜態方法 newProxyInstance( ) 則是可產生一個代理物件，那這樣做跟靜態代理的差別在那裡呢？

首先，靜態代理是一個麵包師需要代理就要實作一個代理，例如有法國麵包師，中國麵包師，火星麵包師…… 這樣一來需要實作不同麵包師的同時有需要實作不同的代理。

使用動態代理，只需要在 InvocationHandler 的實作中一次定義好代理的工作內容，接下來只需要利用 Proxy.newProxyInstance( ) 爲每一位麵包師產出對應的代理就好。一勞永逸，可喜可賀！

<br>

## 文件

[Baker.java](./Baker.java)

[BakerImpl.java](./BakerImpl.java)

[BakerProxyHandler.java](./BakerProxyHandler.java)

[Main.java](./Main.java)