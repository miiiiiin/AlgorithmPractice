package DP;

import java.util.Arrays;
import java.util.Scanner;

// 더 안전한 방식(Bottom Up)
public class BottomUp1 {

    static int[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        D = new int[n + 1];

        for (int i=0; i <= n; i++) { // DP 테이블 -1로 초기화
            D[i] = -1;
        }


        // 가장 작은 문제(이미 아는 값) -> 초기화
        D[0] = 0;
        D[1] = 1;

        for (int i=2; i <= n; i++) { // DP 테이블 -1로 초기화
            D[i] = D[i-1] + D[i-2];

            System.out.println(Arrays.toString(D));
        }

        System.out.println(D[n]);

    }
}
