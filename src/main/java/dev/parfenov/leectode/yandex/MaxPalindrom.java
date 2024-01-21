package dev.parfenov.leectode.yandex;

// https://leetcode.com/problems/longest-palindromic-substring/

/**
 * Дана строка. Найти и вывести максимальную по длине подстроку, являющуюся палиндромом.
 * пример: "kloikkjkabccbaqerty" -> "abccba"
 * <p>
 * Принцип: смотрим соседей. Если они совпадают - начинаем разъезжаться двумя указателями в стороны
 */
public class MaxPalindrom {
    public static void main(String[] args) {
        var s = "kloikkjkabccbaqerty";
//        var s = "aaacbabc";
//        var s = "abb";
//        var s = "a";
        System.out.println(maxPalindrome(s));
    }

    private static String maxPalindrome(String s) {
        var max = "";
        int left, right;
        for (int i = 1; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i - 1) == s.charAt(i + 1)) { //aba строка
                left = i - 1;
                right = i + 1;
            } else if (s.charAt(i - 1) == s.charAt(i)) { //aa строка
                left = i - 1;
                right = i;
            } else continue;

            while (left - 1 >= 0 && right + 1 < s.length() && s.charAt(left - 1) == s.charAt(right + 1)) {
                left--;
                right++;
            }

            var current = s.substring(left, right + 1);
            if (current.length() > max.length())
                max = current;
        }

        return max;
    }
}
