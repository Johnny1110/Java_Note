# 建構者模式

<br>

--------------------------------------

<br>

## 說明

之前在專案部屬 Spring Security 的時候就接觸到 fluent 設計以及建構者模式，那個時候一直不理解這樣做的意義以及方法。最近讀到一些相關文章這邊做一下整理。

首先先看一下建構者模式的使用方式。

    Person person = new Person.Builder()
                              .name("Johnny")
                              .id("E123123123")
                              .address("Taipei xinyi road")
                              .age(20)
                              .PhoneNum(96868686)
                              .build();

不去直接使用 Person 的建構函式而是使用一個內部類別 Builder 使用 fluent 設計填入成員屬性，最後使用 build( ) 建立 Person 實例。

#### 好處 :

這看似多此一舉的方法其實是簡化了複雜類別設計。就拿 Spring Security 舉例好了，Spring Security 的設定部屬如果要將每一個建構函式都設計出來，那類別展開後會變得十分巨大且複雜。建構函式的參數也會很多。

使用建構者模式可以解決上述的問題，而且也是 Java 8+ 所推廣的鑽寫風格。

<br>

## 範例

* Person.java

        public class Person {
            private String name;
            private int age;
            private String id;

            // 設定爲外界不可直接使用 new 實例化。
            private Person(Builder builder) { 
                this.name = builder.name;
                this.age = builder.age;
                this.id = builder.id;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            public String getId() {
                return id;
            }

            public static class Builder {
                private String name = "匿名者";
                private int age = 1;
                private String id = "???";

                // 結尾使用 return this ，是流暢(fluent) 設計。
                public Builder setName(String name){
                    this.name = name;
                    return this;
                }

                public Builder setAge(int age){
                    this.age = age;
                    return  this;
                }

                public Builder setId(String id){
                    this.id = id;
                    return this;
                }

                public Person build(){
                    return new Person(this);
                }
            }
        }

    實際測試 :

        public static void main(String[] args) {
            Person person = new Person.Builder()
                                      .setName("Johnny")
                                      .setId("E123123123")
                                      .setAge(20)
                                      .build();
            System.out.println(person.getName() + " " + person.getId());
        }

這樣一來建構 Person 時更具有彈性，不必被建構函式所限制，只需針對需要的屬性設定就可以了。

<br>

## 文件

* [Person.java](./Person.java)