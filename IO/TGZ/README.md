# TGZ 套件

<br>

使用 apache 的 commons-compress 套件實現不解壓縮讀取 tgz 內文件。

<br>

maven 依賴：

<br>

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-compress</artifactId>
    <version>1.21</version>
</dependency>
```

<br>
<br>

讀取所有檔案內容：

<br>

```java
public static void readEveryFiles(String tgzFilePath){
    Path tgzPath = Paths.get(tgzFilePath);

    try (InputStream fi = Files.newInputStream(tgzPath);
        BufferedInputStream bi = new BufferedInputStream(fi);
        GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
        TarArchiveInputStream ti = new TarArchiveInputStream(gzi)) {

        ArchiveEntry entry;
        while ((entry = ti.getNextEntry()) != null) {
            String filename = entry.getName();
            long leftSize = entry.getSize();
            System.out.println("-----------------------------------------------");
            System.out.println("name: " + filename);
            System.out.println("size: " + leftSize);
            if (leftSize == 0){
                continue;
            }
            byte[] buff;
            int numToRead;
            if (leftSize >= INIT_BUFF_SIZE){
                buff = new byte[INIT_BUFF_SIZE];
                numToRead = INIT_BUFF_SIZE;
            }else{
                buff = new byte[(int) leftSize];
                numToRead = (int) leftSize;
            }


            StringBuilder sb = new StringBuilder();
            int ans = 0;
            while ((ans = ti.read(buff, 0, numToRead)) > 0){
                sb.append(new String(buff, StandardCharsets.UTF_8));
                leftSize = leftSize - numToRead;
                if (leftSize >= INIT_BUFF_SIZE){
                    buff = new byte[INIT_BUFF_SIZE];
                    numToRead = INIT_BUFF_SIZE;
                }else{
                    buff = new byte[(int) leftSize];
                    numToRead = (int) leftSize;
                }
            }

            System.out.println(sb.toString());

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

<br>
<br>

讀取 tgz 壓縮檔內某一個指定檔案：

<br>
<br>

```java
public static String readFileFromTgz(String tgzPathStr, String filename){
    Path tgzPath = Paths.get(tgzPathStr);
    StringBuilder sb = new StringBuilder();
    try (InputStream fi = Files.newInputStream(tgzPath);
        BufferedInputStream bi = new BufferedInputStream(fi);
        GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
        TarArchiveInputStream ti = new TarArchiveInputStream(gzi)) {

        ArchiveEntry entry;
        while ((entry = ti.getNextEntry()) != null) {
            String entryName = entry.getName();
            long leftSize = entry.getSize();
            if (leftSize == 0 || !entryName.equals(filename)) {
                continue;
            }

            byte[] buff;
            int numToRead;
            if (leftSize >= INIT_BUFF_SIZE){
                buff = new byte[INIT_BUFF_SIZE];
                numToRead = INIT_BUFF_SIZE;
            }else{
                buff = new byte[(int) leftSize];
                numToRead = (int) leftSize;
            }

            int ans = 0;
            while ((ans = ti.read(buff, 0, numToRead)) > 0){
                sb.append(new String(buff, StandardCharsets.UTF_8));
                leftSize = leftSize - numToRead;
                if (leftSize >= INIT_BUFF_SIZE){
                    buff = new byte[INIT_BUFF_SIZE];
                    numToRead = INIT_BUFF_SIZE;
                }else{
                    buff = new byte[(int) leftSize];
                    numToRead = (int) leftSize;
                }
            }
            break;
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    return sb.toString();
}
```

<br>
<br>

讀取總行數：

<br>

```java
static int getStringTotalLines(String content) throws IOException {
    StringReader stringReader = new StringReader(content);
    LineNumberReader lineReader = new LineNumberReader(stringReader);
    String s = lineReader.readLine();
    int lines = 0;
    while (s != null) {
        lines++;
        s = lineReader.readLine();
    }
    lineReader.close();
    stringReader.close();
    return lines;
}
```