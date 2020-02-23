# `DataInputStream`、`DataOutputStream`

<br>

`DataInputStream`、`DataOutputStream` 這兩個包裹器是個好東西，裝飾 `InputStream`、`OutputStream` 兩大 IO 類別。如果哪一天想用檔案做持久化可以參考一下。

<br>

## 實作檔案持久化

* [Member.java](./Member.java)

    ```java
    import java.io.*;

    public class Member {
        private String id;
        private String name;
        private int age;

        // constructor getter setter ... 

        public void save(){
            try(DataOutputStream out = new DataOutputStream(new FileOutputStream(this.id))){
                out.writeUTF(this.id);
                out.writeUTF(this.name);
                out.writeInt(this.age);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Member find(String id){
            Member member = null;
            try(DataInputStream in = new DataInputStream(new FileInputStream(id))) {
                member = new Member(in.readUTF(), in.readUTF(), in.readInt());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return member;
        }
    }
    ```

    <br>

* 使用 Member

    ```java
    public static void main(String... args) throws IOException {
        Member man = new Member("J1", "Johnny", 21);
        man.save();

        Member me = Member.find("J1");
        System.out.println(me.getName() + " | " + me.getAge());
    }
    ```