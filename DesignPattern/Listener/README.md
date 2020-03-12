#   

# 製作 Listener 架構

<br>

使用一些框架的時候經常會使用到 `Listener`，例如 __Android__ 或 __Swing__ 裡面經常出現的 `EventListener` 。



事實上，Listener 設計架構的背後是所謂的 __觀察者模式__  。本篇筆記就來實作一下 Linstener 架構吧。



<br>



---



<br>



##  目錄



1. [觀察者模式介紹](#obs)

2. [製作 TaskProcessor](#tp)



<br>

---



<br>

<div id="obs"></div>


## 觀察者模式介紹

* __甚麼是觀察者模式 ?__

    舉生活上的例子就是 youtube 訂閱。A 是一個 youtuber，B 跟 C 是觀眾。B 跟 C 可以選擇訂閱 A ，這樣一來，B 跟 C 就不用自己去 A 的頻道查看有沒有新的影片了。只要 A 更新新影片，A 就會把新影片上架的消息按照 __訂閱者清單__ 發送給所有訂閱者。

    ![1](./imgs/1.png)

    <br>

* __簡易實作__

    




<br>

-----------------

<br>

<div id="tp">

## 製作 TaskProcessor
