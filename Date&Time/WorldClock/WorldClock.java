import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class WorldClock {
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
}