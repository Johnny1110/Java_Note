
import java.util.ArrayList;
import java.util.List;

public class Subscriber {

    private String name;

    private List<Youtuber> subscribeList = new ArrayList<>();

    public Subscriber(String name){
        this.name = name;
    }

    public void noticed(Youtuber youtuber, String videoName){
        System.out.println(String.format("%s 收到新消息 : %s 發布了新影片，<%s>",this.name, youtuber.getName(), videoName));
    }

    public void subscribe(Youtuber youtuber){
        youtuber.addSubscriber(this);
        this.subscribeList.add(youtuber);
    }

    public void cancelSubscribe(Youtuber youtuber){
        youtuber.removeSubscriber(this);
        this.subscribeList.remove(youtuber);
    }
}
