package assignment3;

import java.util.Arrays;
import java.util.Scanner;

//부분 문자열이란 어떤 문자열의 일부(Subsequence)를 의미한다. 두 문자열이 주어졌을 때, 두 문자열에 모두 포함된 가장 긴 공통 부분 문자열의 길이를 반환하는 함수를 작성하시오.
//
//        조건
//        입력
//        문자열은 알파벳 대문자로만 이루어져 있다.
//        문자열의 길이는 1이상 1,000이하이다.
//        C/C++
//        #define을 제외한 별도의 전처리문은 사용할 수 없다. (단, 이미 주어진 것은 사용 가능)
//        예시 1
//        s1 : ABCBD
//        s2 : ACBD
//        ACBD가 가장 긴 공통 부분 문자열이므로 4가 된다.
//
//        예시 2
//        s1 : ABAABA
//        s2 : AA
//        AA가 가장 긴 공통 부분 문자열이므로 2가 된다.
//
//        예시 3
//        s1 : BCDEFGHA
//        s2 : ACDEFGHB
//        CDEFGH가 가장 긴 공통 부분 문자열이므로 6이 된다.

public class LongestCommonString {

    public static int lcs(String a, String b) {
        int m = a.length();
        int n = b.length();

        // dp[i][j]는 s1의 처음 i개 문자와 s2의 처음 j개 문자를 고려할 때
        // 만들 수 있는 가장 긴 공통 부분 문자열의 길이를 나타냄
        int[][] dp = new int[m + 1][n + 1];

        System.out.println(dp.length);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

//                System.out.println(a.charAt(i - 1) + " " + b.charAt(j - 1)); // AAAA:ACBD 이런식으로 비교

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    System.out.println(Arrays.toString(dp[i - 1]) + " " + Arrays.toString(dp[j - 1]));
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    System.out.println(dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                    System.out.println(dp.length);

                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.next();
        String b = scanner.next();

        // 두 문자열의 모든 부분 문자열에 대해 최장 공통 부분 문자열 계산
        int result = lcs(a, b);
        System.out.println(result);
    }
}


