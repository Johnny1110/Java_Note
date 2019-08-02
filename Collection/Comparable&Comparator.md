# Comparable & Comparator

<br>

-------------------------------

<br>

## 說明

一直忘記 Comparable 和 Comparator 的排序機制，乾脆就直接記錄下來以後方便複習吧。

Collection 介面物件需要排序的話一般都依賴於 Comparable 和 Comparator 這兩個介面，如這兩個介面的名子就可以得知，Comparable 是排序的目標物件需實作的介面 ( 實作 Comparable 就是可比較對象 )，Comparator 則是需要另外實作的一個排序工具介面。

Comparable 作用在 TreeSet 、 TreeMap 中，搭配 Treexxx 使用可以讓集合中的物件自動進行排序。

Comparator 則是可以作用於有 sort 方法的集合物件中。

<br>

## 範例

1. ### Comparable 的使用

    先定義一個 Person.java:

        public class Person implements Comparable<Person> {

            private String name;
            private  int rank; // 排序標準

            public Person(String name, int rank){
                this.name = name;
                this.rank = rank;
            }

            //--  getter & setter  --//

            @Override
            public int compareTo(Person o) {
                return this.rank - o.rank;
            }

            @Override
            public  String toString(){
                return this.name + " " + this.rank;
            }
        }

    這邊 Person 直接實作 Comparable 介面，覆寫 compareTo 方法，依傳回的整數值判定排序方式。

    * compareTo = 0  表示兩者相等不動作。

    * compareTo < 0 表示 this 小於 other，this 要排在 other 前面。

    * compareTo > 0 表示 this 大於 other，this 要牌在 other 後面。

    其實簡單理解在 compareTo( ) 中， this - other 就是升冪排法，other - this 就是降冪排法。

    執行看看吧 ：

        public static void main(String[] args) {

            Set<Person> personSet = new TreeSet<>();
            personSet.add(new Person("Johnny", 1000));
            personSet.add(new Person("Jarvan", 1600));
            personSet.add(new Person("kid", 1400));
            personSet.add(new Person("Babala", 1100));
            personSet.forEach(System.out::println);

            System.out.println("------------------------");

            Map<Person, Integer> personMap = new TreeMap<>();
            personMap.put(new Person("Johnny", 1000),1);
            personMap.put(new Person("Jarvan", 1600),2);
            personMap.put(new Person("kid", 1400),3);
            personMap.put(new Person("Babala", 1100),4);
            personMap.forEach((i, p) ->{
                System.out.println("key: " + i + " value: " + p);
            });
        }

    output :

        Johnny 1000
        Babala 1100
        kid 1400
        Jarvan 1600
        ------------------------
        key: Johnny 1000 value: 1
        key: Babala 1100 value: 4
        key: kid 1400 value: 3
        key: Jarvan 1600 value: 2

 可以看到 TreeSet 很簡單，單純排序而以。TreeMap 要稍微注意一下，它不是排序 value，而是排序 key ( 被擺了一道，一直執著是排序 value ) 。

<br>
<br>

 2. ### Comparator 的使用。

        public static void main(String[] args) {
                List<Person> people = new ArrayList<>();
                people.add(new Person("Johnny", 1000));
                people.add(new Person("Jarvan", 1600));
                people.add(new Person("kid", 1400));
                people.add(new Person("Babala", 1100));

                Comparator<Person> comparator = new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o1.compareTo(o2);
                    }
                };

                people.sort(comparator);
                people.forEach(System.out::println);
            }

    output :

        Johnny 1000
        Babala 1100
        kid 1400
        Jarvan 1600

    * 這裏就直接使用匿名類別實作 Comparator 介面定義 compare( )。

    * 與 Comparable 的 compareTo( ) 一樣，compare( ) 回傳的整數也有一樣的意義。

        * compare = 0  表示兩者相等不動作。

        * compare < 0 表示 01 小於 o2，o1 要排在 o2 前面。

        * compare > 0 表示 o1 大於 o2，o1 要牌在 o2 後面。

    * 使用 Comparator 的時候可以搭配 Lambda ( 炫技XD~ )，這邊就不示範了，怕太隴長。