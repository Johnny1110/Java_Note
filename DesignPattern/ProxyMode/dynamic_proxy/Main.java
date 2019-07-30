public class Main {
    public static void main(String[] args) {
        BakerProxyHandler bakerProxyHandler = new BakerProxyHandler();
        Baker baker = (Baker) bakerProxyHandler.getProxyInstance(new BakerImpl());
        baker.sellCroissant();
        baker.sellCustardBread();
        baker.sellTaiwanLonganWithRedWineBread();
    }
}
