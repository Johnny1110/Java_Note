# `InputStreamReader` 、 `OutputStreamWriter`

<br>

`InputStreamReader` 、 `OutputStreamWriter` 大家看名字也知道，對應的分別是 `InputStream` 、 `OutputStream`。

`InputStream` 、 `OutputStream` 是操作位元組，使用 `InputStreamReader` 、 `OutputStreamWriter` 包裹器就可以直接變成操作字元了。

<br>

## 範例

* 這邊範例就重構一下 IO Stream 版的 `dump()` 方法使用 `InputStreamReader` 、 `OutputStreamWriter` :

    ```java
    public static void dump(InputStream in, OutputStream out){
        try(Reader reader = new InputStreamReader(in);
            Writer writer = new OutputStreamWriter(out)){
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