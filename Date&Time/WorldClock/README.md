# 實作世界時鐘

<br>

---------------------------

<br>

## 概要

國際時間的處理還是比較麻煩的，除了有 2 種不同的時間格式 (GMT - UTC) 之外，還涉及到時間偏移 ( Offset ) 和日光節約時間( D.S.T )。具體名詞的意思這邊不做解釋，主要實作一下世界時鐘來玩玩看。

Java 的 time API 有套件可以處理世界時間轉換問題，如果要自己手動去算就有點麻煩，不只是是要考量時間偏移量，重點是還要算日光節約時間，官方有觸理方法用官方的套件再好不過。這邊紀錄一下吧

<br>

## 實作

用 Asia/Taipei 時區來轉換成美國紐約時間 ：

    public static void main(String[] args) {
            LocalDateTime now = LocalDateTime.now();

            ZoneId taipei = ZoneId.of("Asia/Taipei");
            ZonedDateTime taipeiTime = ZonedDateTime.of(now, taipei); // 取得臺北地區時間。
            OffsetDateTime offset = taipeiTime.toOffsetDateTime(); // <地區時間> 轉換爲 <可位移時間>。

            ZoneId newyork = ZoneId.of("America/New_York");
            ZonedDateTime newyorkTime = offset.atZoneSameInstant(newyork); // 取得同時間的 newyork 地區時間。

            System.out.println("time in taipei : " + taipeiTime);
            System.out.println("time in newyork : " + newyorkTime);
        }


output :

    time in taipei : 2019-08-05T20:11:24.629242+08:00[Asia/Taipei]
    time in newyork : 2019-08-05T08:11:24.629242-04:00[America/New_York]


<br>

## 文件

[WorldClock.java](./WorldClock.java)