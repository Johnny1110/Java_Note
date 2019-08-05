import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeDemo {
    public static void main(String[] args) {
        // 現在時間
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("現在時間 : %s \n", now);

        // 定義某一時間(用 LocalDate & LocalTime)
        LocalDate myBirthdayDate = LocalDate.of(1998,11,10);
        LocalTime myBirthdayTime = LocalTime.of(20, 41);
        LocalDateTime myBirthday = LocalDateTime.of(myBirthdayDate, myBirthdayTime);
        System.out.printf("我的生日 : %s \n", myBirthday);

        // 定義某一時間(直接定義) <2019/8/15  21:21>
        LocalDateTime oneDay = LocalDateTime.of(2019, Month.AUGUST, 15, 21, 21);
        System.out.printf("某一天 : %s \n", oneDay);

        // 日期加減
        LocalDateTime anotherDay = oneDay.plusDays(5).plusHours(5).plusMinutes(5);
        System.out.printf("某天加5天5小時5分鐘 : %s \n", anotherDay);

        // 日期差額
        System.out.printf("oneDay 與 anotherDay 差幾天 ?  ans : %s \n",oneDay.until(anotherDay, ChronoUnit.DAYS));
        System.out.printf("oneDay 與 anotherDay 差幾時 ?  ans : %s \n",oneDay.until(anotherDay, ChronoUnit.HOURS));
        System.out.printf("oneDay 與 anotherDay 差幾分 ?  ans : %s \n",oneDay.until(anotherDay, ChronoUnit.MINUTES));
    }
}