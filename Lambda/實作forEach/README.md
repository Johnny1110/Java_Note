# 實作 forEach

<br>

-------------------------

<br>

## 概要

用了很久 forEach，但是不知道 forEach 如何實現，趁還記一點觀念就自己實作一個 forEach 吧。

<br>

## 文件

* [Main.java](./Main.java)
* [JohnnyList.java](./JohnnyList.java)
* [JohnnyListImpl.java](./JohnnyListImpl.java)
* [Consumer.java](./Consumer.java)

<br>

## 重點

* forEach 是基於 Java 1.8+ 的 default function 與 Lambda 來實現的。目前在官方 JDK 中定義在 Iterable 介面，所有子類別都可以使用 forEach( ) 這個預設方法。

* 因為要看一下 forEach( ) 的實作原理，所以就自己定義了一個 JohnnyList 介面( 名字真的想不到 )，在其中定義了 forEach( ) 預設方法。JohnnyListImpl 作為 JohnnyList 實現，實作出了一個簡單功能的 ArrayList。

* Consumer 是我定義的一個 Functional Interface，作為一個支援 forEach 歷遍處裡的代理 ( 之後用 Lambda 代替匿名類別往裡面填實現 ) 

* Main 是最終調用實驗。

