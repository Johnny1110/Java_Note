import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private static final String rule = "^[-+\\d][\\d]*";
    private static Pattern pattern = Pattern.compile(rule);

    public int myAtoi(String str) {
        int result = 0;
        Matcher matcher = pattern.matcher(str.trim());
        if(matcher.find()) {
            str = matcher.group();
            if (!str.equals("-") && !str.equals("+")){
                try {
                    result = Integer.valueOf(str);
                } catch (NumberFormatException ex) {
                    result = str.charAt(0) != '-' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }
        }
        return result;
    }
}