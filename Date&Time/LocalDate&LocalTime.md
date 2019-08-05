# 有趣的 LocalDate 和 LocalTime

<br>

---------------------------------------------

<br>

## 概要

準備 OCPJP 的複習期間遇到了兩個有趣的類別 LocalDate 和 LocalTime。這邊簡單紀錄一下吧。

LocalDate 和 LocalTime 基於 JDK 1.8+， 定義當地( Local ) 的日期時間。 如果只是簡單的定義日期而以當然不值得紀錄。牠們可以基於一個指定時間透過一些方法得到更多衍生資訊，直接看範例比較快。

還有一個類別是把 LocalDate 和 LocalTime 結合起來，叫做 LocalDateTime。這邊也稍微介紹一下。

<br>

1. [LocalDate 使用示範](#1)

2. [LocalTime 使用示範](#2)

3. [LocalDateTime 使用示範](#3)

<br>

## 範例

<span id="1">

* LocalDate 使用示範

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

<br>

<span id="2">

* LocalTime 使用示範

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

<br>

<span id="3">

* LocalDateTime 使用示範

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



<br>
<br>

## 文件

* [LocalDateDemo.java](./LocalDateDemo.java)

* [LocalTimeDemo.java](./LocalTimeDemo.java)

* [LocalDateTimeDemo.java](./LocalDateTimeDemo.java)