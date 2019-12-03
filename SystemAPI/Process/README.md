# Java 命令列呼叫 -> Runtime Process

<br>

-----------------------

<br>

## 簡介

在 xx 公司實習階段遇到一個問題，使用 java 呼叫 python 直譯器執行 .py 文件有很多坑。

過程中遇到一些問題，如何印出 console 與 error，還有子執行緒錯誤無法被偵測到中斷，錯誤資訊也無法獲取。這邊都詳細說明並解決。

<br>
<br>

## 實作

### [CMDCaller.java](./CMDCaller.java)


*   首先實作 java 的部分，java 主要負責開啟 Process 並進行命令列呼叫。

    ```java
    Process process = Runtime.getRuntime().exec("cmd 命令");
    ```

    執行 exec 後會返回 cmd 資訊，包括執行 console 與 error。

    值得注意的是，exec() 可以接受多行 cmd 命令，只需要用 & 符號分隔就可以了例如

    ```bash
    cd ./Script/bin/activate && python --version
    ```

<br>

*   有一個阻塞方法，會等到命令列執行結束並返回離開代碼，一般 0 代表正常離開，1 代表有異常，並中斷執行開。

    ```java
    int exitCode = process.waitFor();
    ```



<br>

* 執行後的 console 與 error 資訊要分別取出。例如:

    ```java
    try(BufferedReader printerReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))){

                printerReader.lines().forEach(msg ->{
                    System.out.println("python console : " + msg);
                });

                errorReader.lines().forEach(msg ->{
                    System.out.println("python error : " + msg);
                });
            }
    ```

<br>
<br>

### [RunThread.py](./RunThread.py)

*   因為 java process 那邊是針對 `RunThread.py` 執行過程進行監聽。`RunThread.py` 開啟了 3 個子執行緒執行。子執行緒的錯誤並不會被 process 所獲取，所以當子執行緒出錯，process 並不知道，exitCode 也始終是 0 (正常退出)。

解決方法就是用一個 queue 去接所有執行緒的錯誤，主執行緒用 while 迴圈去檢查 queue 是否為 Empty，當偵測到 queue 裡面有 Exception 時就拋出錯誤。

因為篇幅過長，所以實作細節就以註解方式寫到 [RunThread.py](./RunThread.py) 裡面。
