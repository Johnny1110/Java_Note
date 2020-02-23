# `Reader` & `Writer` 快速導覽

<br>

針對字元資料讀取，Java SE 提供 `java.io.Reader` 抽象類別，針對字元寫入則提供 `java.io.Writer` 抽象類別。

<br>

## 實作 `dump()`

* 使用 `Reader` & `Writer` 快速實作一個從來源讀取字元資料，再把資料寫入到目的地。

    ```java
    public static void dump(Reader in, Writer out){
        try(in; out){ // try 結束自動關閉 IO 資源
            char[] data = new char[1024];
            int length = 0;
            while ((length=in.read(data)) != -1){
                out.write(data, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ```

    <br>

* 如果有看過 IO Stream 實作的 `dump()` 會發現，除了讀取資料的載體由 `byte[]` 改為 `char[]`，其他完全沒變。