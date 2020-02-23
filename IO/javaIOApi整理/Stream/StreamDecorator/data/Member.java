import java.io.*;

public class Member {
    private String id;
    private String name;
    private int age;

    public Member(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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