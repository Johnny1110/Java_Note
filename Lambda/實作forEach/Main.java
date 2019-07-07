public class Main {
    public static void main(String[] args) {
        //-- 建立測試對象 --//
        JohnnyList<String> list = new JohnnyListImpl<>();
        list.add("Johnny");
        list.add("Jarvan");
        list.add("Benji");

        // 1. 基本模式，建立 Consumer 的匿名實作傳入 forEach();
        basicMode(list);

        // 2. 利用 Lambda 簡化 basicMode。
        lambdaMode(list);

        // 3. 利用 Lambda 的方法參考技巧，參考 System.out 的 println 方法實作 Consumer 的 accept。
        lambdaFunctionReference(list);
    }


    private static void basicMode(JohnnyList list) {
        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        };
        list.forEach(consumer);
    }


    private static void lambdaMode(JohnnyList<String> list) {
        list.forEach(n ->{
            System.out.println(n);
        });
    }


    private static void lambdaFunctionReference(JohnnyList<String> list) {
        list.forEach(System.out::println);
    }
}
