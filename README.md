# Java 實作筆記

<br>

--------------------------


## 簡介 :

這裏是我把學習 Java 過程中遇到的有趣的想法或實作彙整的地方，方便以後拿來複習或者改版升級。

<br>

## 目錄

<br>

### 常用

* [開 maven 新專案 pom.xml(基本款)](./maven/basic/pom.xml)

* [開 maven 新專案 pom.xml(Spring Boot)](./maven/spring_boot/pom.xml)

* [JWT integration class](/JWT)

<br>

### 1. IO / NIO

* [java IO API 整理](./IO/javaIOApi整理)

* [資料夾刪除與複製](./IO/資料夾刪除與複製)

* [WatchService 實作](./IO/WatchService實作)

* [Channel IO](./IO/Channel_IO/.README.md)

* [TGZ 套件類（不解壓縮讀取 tgz 內文件內容）](./IO/TGZ/README.md)

<br>

### 2. JGit

* [用 Java 操作 git 歷遍 commit](./JGit/JGitWalkCommit)

* [JGit clone git-server 上的專案](./JGit/JGitCloneRepo)

* [使用 Deamon 重構 clone git repo](./JGit/DeamonRefactor)

<br>

### 3. POI

* [Java 產出 Office 文件系列工具](./POI)

<br>

### 4. Lambda

* [實作 forEach](./Lambda/實作forEach)
* [Group By](./Lambda/group_by)

<br>


### 5. Thread

* [CyclicBarrier 執行緒的緩衝](./Thread/CyclicBarrier)

* [ExecutorService 實作](./Thread/ExecutorService)

* [Fork & Join 框架實作費氏數](./Thread/Fork&Join)

* [最新的 Async 做法： CompletableFuture](./Thread/completable_future)

<br>

### 6. Collection

* [Comparable & Comparator](./Collection/Comparable&Comparator)

<br>

### 7. Date & Time (jdk 1.8+)

* [有趣的 LocalDate 和 LocalTime](./Date&Time/LocalDate&LocalTime)

* [實作世界時鐘](./Date&Time/WorldClock)

<br>

### 8. Regex

* [1.LeetCode 題目: string-to-integer-atoi](./Regex/atio)

<br>

### 9. Design Pattern

* [代理模式](./DesignPattern/ProxyMode)

* [建構者模式](./DesignPattern/builderPattern)

* [製作 Listener (觀察者模式應用)](./DesignPattern/Listener/README.md)

* [裝飾器（Decorator）設計](./DesignPattern/Decorator/README.md)

* [簡易工廠模式設計](./DesignPattern/Factory/README.md)

* [創建和銷毀物件](./DesignPattern/CreateAndDestory/README.md)

<br>

### 10. Java Crawler

* [按照日期爬 PTT 八卦版資料](./JavaCrawler/PttGossipCrawler)

<br>


### 11. 日誌

* [配置與使用 Log4j2](./javaLogger/Log4j2)

<br>

### 12. SystemAPI

*   [java 呼叫命令列執行](./SystemAPI/Process)

<br>

### 13. 演算法

*   [25 的乘階（25!）怎麼算 ?](./Algorithm/Factorial/README.md)

*   [數字的加總組合](./Algorithm/SumCombination/README.md)

*   [捲積運算](./Algorithm/Convolution/README.md)

* [深度優先搜尋（DFS）](./Algorithm/DFS/README.md)

<br>

### 14. Gradle

*   [Gradle 整合大型專案](./Gradle/structuringLargeProjects/README.md)

<br>

### 15. SQL 類

*   [MYSQL 把多筆資料合併成一筆 `GROUP_CONCAT()`](./SQL/GROUP_CONCAT/README.md) 

<br>

### 16. Java 通用工具類

*   [檢查 JavaBean 的 Field 是否恰當填值 （不為空字串或 null）](./common/checkJavaBean/README.md) 

<br>

### 17. WebService

* [發送 SOAP 請求](./webservice/soap/sendSOAPRequest.md)

<br>
<br>
<br>
<br>

 ### +. 犯錯合集

 * [Thread sleep() 對象鎖 wait() notifyAll()](./MistakeCollection/ThreadMistake.md)

 * [Thread 錯誤的鎖對象](./MistakeCollection/wrongSynchronized.md)

 <br>
 <br>

 ### +. Java Projects

 * [JDK]()


<br>
<br>

  ### +. 其他

 * [Kafka 安裝教學](./others/kafka_install/README.md)
