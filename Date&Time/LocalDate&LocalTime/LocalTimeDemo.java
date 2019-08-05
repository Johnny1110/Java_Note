import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalTimeDemo {
    public static void main(String[] args) {
        // 取得現在時間
        LocalTime now = LocalTime.now();
        System.out.printf("現在時間 : %s \n", now);

        // 時間加減
        System.out.printf("現在時間加3個半小時 : %s \n", now.plusHours(3).plusMinutes(30).plusSeconds(20));

        // 時間格式截斷
        System.out.printf("擷取時間到秒單位 : %s \n", now.truncatedTo(ChronoUnit.MINUTES));

        // 從午夜開始
        System.out.printf("從午夜開始過了幾秒 ? ans: %s \n", now.toSecondOfDay());
        System.out.printf("從午夜開始過了幾奈秒 ? ans: %s \n", now.toNanoOfDay());

        // 時間比較
        LocalTime target = LocalTime.of(13,30);
        System.out.printf("target 在現在之前嗎 ？ ans : %s \n", target.isBefore(now));
        System.out.printf("target 在現在之後嗎 ？ ans : %s \n", target.isAfter(now));

        // 時間差
        LocalTime target1 = LocalTime.of(13,30);
        System.out.printf("target1 跟現在差幾小時 ？ ans : %s \n",now.until(target1, ChronoUnit.HOURS));
        System.out.printf("target1 跟現在差幾分鐘 ？ ans : %s \n",now.until(target1, ChronoUnit.MINUTES));
    }
}