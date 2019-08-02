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
        private String name;
        private int age;
        private String id;
        
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