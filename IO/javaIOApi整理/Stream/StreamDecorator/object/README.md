# `ObjectInputStream`、`ObjectOutputStream`

<br>

`DataInputStream`、`DataOutputStream` 的作用是存取 java 的資料，而 `ObjectInputStream`、`ObjectOutputStream` 則可以直接把 java 物件的當時狀態做存取。

而可以被 `ObjectInputStream`、`ObjectOutputStream` 存取的物件必須 implements `java.io.Serializable` 介面。

<br>

## 實作

* [Member.java](./Member.java)

    ```java
    import java.io.*;

    public class Member implements Serializable {
        private String id;
        private String name;
        private int age;

        // constructor getter setter ... 

        public void save(){
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.id))){
                out.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Member find(String id){
            Member member = null;
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(id))) {
                member = (Member) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return member;
        }
    }
    ```

    <br>

* 如果物件在序列化時，某個屬性不想被寫入
可以宣告為 `transient` : 

    ```java
    private transient int age;
    ```