# LeetCode 題目: string-to-integer-atoi

<br>

------------------------

<br>

## 說明

題目原網址 : https://leetcode.com/problems/string-to-integer-atoi/

這道題目蠻刁鑽的，前面實作的時候沒有想到用 regex，直接硬寫，然後就一直測不過。其實硬寫邏輯是可以解決啦，只不過太過於攏長的語法以及難以 review 的邏輯讓我不得不去想其他解，最終鎖定在了 regex 上。看到其他人分享的解法幾乎也都是硬解，動則三四十行加繞一大堆邏輯，想到用 regex 有點暗爽呀。

<br>

## 題目

Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

#### Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

<br>

## 實作

Solution.java :

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

<br>

## 檔案

[Solution.java](./Solution.java)