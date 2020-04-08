import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Factorial {

    private List<Integer> numBuffer = new ArrayList<>();

    public String calculate(int target){
        putNumToLastOfNumBuffer(target);
        for (int i = target - 1; i > 0; --i){
            System.out.println("正在乘: " + i);
            multiplyWithCurrentNumBuffer(i);
            System.out.println("當前 numNuffer: " + numBuffer);
        }

        Collections.reverse(numBuffer); // 算完記得把陣列反轉回來才是正確值。
        StringBuilder sb = new StringBuilder();
        numBuffer.forEach(sb::append);
        return sb.toString();
    }

    private void multiplyWithCurrentNumBuffer(int num) {
        int carryBuff = 0; // 進位緩存
        int currentNumSize = numBuffer.size();

        for (int i = 0; i <= currentNumSize; i++){
            if (i == currentNumSize){  // 當已經是最大位數時
                if (carryBuff > 0){
                    putNumToLastOfNumBuffer(carryBuff);
                    carryBuff = 0;
                }
                break;
            }

            int currentDigits = numBuffer.get(i);

            if (i == 0 && currentDigits == 0) {
                continue;
            }

            if (currentDigits == 0) {
                numBuffer.set(i, carryBuff%10);
                carryBuff = (int)Math.floor((double) (carryBuff/10));
                continue;
            }

            System.out.println("第" + (i+1) + "位數 " + "box = " + num + "*" + currentDigits + "+" + carryBuff);
            int box = num * currentDigits + carryBuff;
            numBuffer.set(i, box%10);
            carryBuff = (int)Math.floor((double) (box/10));
            System.out.println("carryBuff: " + carryBuff);
        }
    }

    private void putNumToLastOfNumBuffer(int num) {
        List<String> splits = Arrays.asList(String.valueOf(num).split(""));
        Collections.reverse(splits); // 反轉陣列 ex: [1, 0, 0] -> [0, 0, 1]
        splits.forEach(n -> {
            numBuffer.add(Integer.valueOf(n));
        });
    }

}