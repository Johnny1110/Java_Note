import java.io.*;

public class Member implements Serializable {
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