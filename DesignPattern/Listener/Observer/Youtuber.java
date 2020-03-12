
import java.util.ArrayList;
import java.util.List;

public class Youtuber {

    private String name;

    private List<Subscriber> subscriberList = new ArrayList<>();

    public Youtuber(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addSubscriber(Subscriber subscriber){
        this.subscriberList.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber){
        this.subscriberList.remove(subscriber);
    }

    public void publishVideo(String viedoName){
        this.subscriberList.forEach(subscriber -> subscriber.noticed(this, viedoName));
    }
}
