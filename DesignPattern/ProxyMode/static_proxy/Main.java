public class Main {
    public static void main(String[] args) {
        Baker baker = new BakerProxy(new BakerImpl());
        baker.sellCustardBread();
        baker.sellTaiwanLonganWithRedWineBread();
        baker.sellCroissant();
    }
}
