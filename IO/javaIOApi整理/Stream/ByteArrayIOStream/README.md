# ByteArrayInputStream 與 ByteArrayOutputStream

## 範例

```java
byte[] inBox = {1, 2, 3, 4, 5};
byte[] outBox;
try(ByteArrayInputStream in = new ByteArrayInputStream(inBox);
    ByteArrayOutputStream out = new ByteArrayOutputStream()){
    byte[] data = new byte[5];
    int length = 0;
    while((length = in.read(data)) != -1){
        out.write(data, 0, length);
        outBox = out.toByteArray();
    }
}
```