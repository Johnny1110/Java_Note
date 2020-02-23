# 標準輸入輸出

## `System.in`

* `System.in`（標準輸入） 與 `System.out`（標準輸出） 分別是 `InputStream` 與 `OutputStream` 的實例。

* 我們建立 Scanner 物件時通常會如下編寫 :

    ```java
    Scanner scanner = new Scanner(System.in);
    ```

    仔細看一下，Scanner 建構函式耀球輸入一個 InputStream 實例，而 `System.in` 預設對應到我們文字介面的輸入。我們其實也可以重新指定標準輸入的資料來源。

    ```java
    System.setIn(new FileInputStream("D:/buffer/logger.py"));
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()){
        System.out.println(scanner.nextLine());
    }
    ```
    
    使用 `setIn()` 重新指定，這樣一來 System.in 就變成我們指定的輸入來源了。

<br>
<br>

---

<br>
<br>

## `System.out`

* `System.out` 為 `PrintStream` 實例，身為標準輸出，依樣也可以被當成 `OutputStream` 使用。

    像是之前的 dump 方法也可以這樣用 :

    ```java
    InputStream in = new FileInputStream("D:/buffer/logger.py");
    Dumper.dump(in, System.out);
    ```

    如此一來，直接可以在 console 上看到輸出內容。

    <br>

* 標準輸出也可以重導向，例如寫了一個 Hello.java 文件，裡面寫一個 `System.out.println("Hello Wolrd");`，我們編譯過後執行時可以把輸出結果導向倒文件中 : 

    ```bash
    java Hello.class > Hello.text
    ```
    如此一來，就可以在 Hello.text 看到輸出結果。

    <br>

* `System.out` 也可以重新指定標準輸出目的地。使用 `setOut()` :

    ```java
    FileOutputStream out = new FileOutputStream("D:/buffer/test.txt");
    PrintStream file = new PrintStream(out);
    System.setOut(file);
    System.out.println("Hello World !");
    file.close();
    out.close();
    ```
    