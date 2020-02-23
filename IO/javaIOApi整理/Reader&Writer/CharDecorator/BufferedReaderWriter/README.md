# `BufferedReader` 、 `BufferedWriter`

<br>

看到前面加了 Buffered，這邊就不必多解釋了，`BufferedReader` 、 `BufferedWriter` 建構實例時，需要分別帶入 `Reader` 與 `Writer` 實例，可以達到緩衝區效果。

<br>

## 範例

* 依舊是 `dump()` 方法 : 

    ```java
    public static void dump(Reader in, Writer out){
        try(BufferedReader reader = new BufferedReader(in);
            BufferedWriter writer = new BufferedWriter(out)){
            char[] data = new char[1024];
            int length = 0;
            while((length = reader.read(data)) != -1){
                writer.write(data, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ```
