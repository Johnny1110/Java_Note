public class BakerProxy implements Baker {

    private Baker baker; // 真正麵包師

    public BakerProxy(Baker baker){
        this.baker = baker;
    }

    private void gretting() {
        System.out.println("您好，歡迎光臨！");
    }

    private void accounting() {
        System.out.println("找錢跟開發票！");
    }

    public String sellCroissant() {
        gretting();
        accounting();
        return baker.sellCroissant();
    }

    public String sellTaiwanLonganWithRedWineBread() {
        gretting();
        accounting();
        return baker.sellTaiwanLonganWithRedWineBread();
    }

    public String sellCustardBread() {
        gretting();
        accounting();
        return baker.sellCustardBread();
    }
}
