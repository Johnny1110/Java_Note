import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class LocalDateDemo {
    public static void main(String[] args) {
        // 使用 LocalDate 的靜態方法 now() 取得今天日期 。
        LocalDate today = LocalDate.now();
        System.out.printf("今天日期 ： %s \n", today);

        // 定義過去的某一天日期。
        LocalDate myBirthday = LocalDate.of(1998,11,10);
        System.out.printf("我的生日 ： %s \n", myBirthday);

        // 日期先後比較
        System.out.printf("我的生日在今天之前嗎？ ans: %b \n",myBirthday.isBefore(today));
        System.out.printf("我的生日在今天之後嗎？ ans: %b \n",myBirthday.isAfter(today));
        System.out.printf("我的生日在今天嗎？ ans: %b \n",myBirthday.isEqual(today));

        // 閏年檢查
        System.out.printf("我的生日是閏年嗎 ？ ans: %b \n", myBirthday.isLeapYear());

        // 日期衍伸資訊
        System.out.printf("我的生日是禮拜幾？ ans : %s \n", myBirthday.getDayOfWeek());
        System.out.printf("我的生日是一個月的第幾天？ ans : %s \n", myBirthday.getDayOfMonth());
        System.out.printf("我的生日是一年的第幾天？ ans : %s \n", myBirthday.getDayOfYear());

        // 時間加減
        System.out.printf("生日加ㄧ天 ： %s \n", myBirthday.plusDays(1)); // QQ 光棍節
        System.out.printf("生日加一月 ： %s \n", myBirthday.plusMonths(1));
        System.out.printf("生日加一年 ： %s \n", myBirthday.plusYears(1));
        System.out.printf("生日減一天 ： %s \n", myBirthday.minusDays(1));
        System.out.printf("生日減一月 ： %s \n", myBirthday.minusMonths(1));
        System.out.printf("生日減一年 ： %s \n", myBirthday.minusYears(1));

        // 使用時間調節器 -> TemporalAdjuester
        System.out.printf("下個禮拜一是幾月幾號 : %s \n", today.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        System.out.printf("上個禮拜一是幾月幾號 : %s \n", today.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
    }
}