package DP;

import java.util.Scanner;

public class BOJ2193 {
    static long[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 0과 1
        arr = new long[N+1][2];

        // 초기화
        // 1자리 이친수 : 1로 끝나는 경우 (1자리 이친수는 1한가지)
        arr[1][1] = 1;
        // 1자리 이친수 : 0으로 끝나는 경우 없음
        arr[1][0] = 0;

        for (int i=2; i<=N; i++) {
            // i자리에서 끝이 0인 경우 (0은 이전 자리가 0과 1로 끝나는 경우에 붙일 수 있음)
            arr[i][0] = arr[i-1][0] + arr[i-1][1];
            // i자리에서 끝이 1인 경우 (1은 이전 자리가 0으로 끝나는 경우에만 붙일 수 있음)
            arr[i][1] = arr[i-1][0];
        }

        // N자리 이친수 총 개수
        System.out.println(arr[N][0] + arr[N][1]);
    }
}
