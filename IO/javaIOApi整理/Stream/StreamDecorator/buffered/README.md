# `BufferedInputStream`、`BufferedOutputStream`

<br>

在沒學會使用 `BufferedInputStream`、`BufferedOutputStream` 之前，每次呼叫 `inputStream.read()` 方法就會直接向來源索取資料，每次呼叫 `outputStream.write()` 方法就會直接將資料寫入目的地，這並不是一個好的方法。

每次 `read()`、`write()` 都會直接要求讀寫硬碟。會花出許多不必要時間在磁碟定位上。

使用 `BufferedInputStream`、`BufferedOutputStream` 就可以避免不必要的時間資源浪費。

<br>
<br>

## 基本概念

### `BufferedInputStream`

* 使用 `BufferedInputStream` 時，我們可以定義一個較大的緩衝區，`InputStream` 就會一口氣讀滿 Buffer 大小的資料。每次使用 `read()` 時，都會先檢查 Buffer 裡面是否還有資料，如果 Buffer 裡的資料都被讀完了，再取來源處撈 Buffer 大小的資料。如此一來可以減少直接讀取資料的次數。

<br>

### `BufferedOutputStream`

* 使用 `BufferedInputStream` 時，我們每次呼叫 `write()` 方法時並不會直接寫入資料到目的地，而是先把資料寫到 Buffer 裡面，一旦我們寫入的資料滿了，或者呼叫 `flash()`，再或者呼叫 `close()` 時，資料才會真正的被寫入。同樣是為了減少直接寫入資料的次數。

<br>
<br>

## 實作

* 實際上除了建構之外，用法跟一般 Stream 並無差別，重構 `dump()` :

    ```java
    public static void dump(InputStream in, OutputStream out){
        try(BufferedInputStream bin = new BufferedInputStream(in);
            BufferedOutputStream bout = new BufferedOutputStream(out)){
            byte[] data = new byte[1024];
            int length = 0;
            while((length = in.read(data)) != -1){
                out.write(data, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ```

