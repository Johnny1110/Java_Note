# FileInputStream 與 FileOutputStream

<br>

`FileInputStream` 是 `InputStream` 的具體化子類，可以指定檔案路徑建構物件，一旦類別實例化為物件，檔案就等於被開啟，可供<strong>讀取</strong>資料。

`FileOutputStream` 是 `OutputStream` 的具體化子類，可以指定檔案路徑建構物件，一旦類別實例化為物件，檔案就等於被開啟，可供<strong>寫入</strong>資料。

<br>

## 注意

* `FileInputStream` 繼承 `InputStream` 主要實作了 `read()` 抽象方法；`FileOutputStream` 繼承 `OutputStream` 主要實作了 `write()` 抽象方法。這兩個 Stream 在讀寫檔案時都是以 byte 為單位在做處理。通常我們都會用 Reader 跟 Writer 把他們包起來，變成以 Char 為單位操作。這個後面再說。

* 範例 code

    ```java
        try(InputStream in = new FileInputStream("C:/a.txt");
            OutputStream out = new FileOutputStream("C:/b.txt")){
            byte[] data = new byte[1024];
            int length = 0;
            while((length = in.read(data)) != -1){
                out.write(data, 0, length);
            }
        }
    ```

