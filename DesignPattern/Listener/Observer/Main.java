
public class Main {
    public static void main(String[] args) {
        Youtuber oldKao = new Youtuber("老高");
        Subscriber johnny = new Subscriber("強尼");
        Subscriber betali = new Subscriber("貝塔里");

        johnny.subscribe(oldKao);
        betali.subscribe(oldKao);

        oldKao.publishVideo("外星人");

    }
}
