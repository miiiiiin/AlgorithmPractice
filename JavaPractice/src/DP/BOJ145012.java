package DP;

import java.util.Scanner;

public class BOJ14501 {
    static int[] arr;
    static int[] T;
    static int[] P;
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N+2];
        T = new int[N+1];
        P = new int[N+1];

        for (int i=1; i<=N; i++) {
            // 상담을 완료하는데 걸리는 기간
            T[i] = sc.nextInt();
            // 상담을 했을 때 받을 수 있는 금액
            P[i] = sc.nextInt();
        }

        // 역순으로 최대 수익 계산
        for (int i=N; i>0; i--) {
            if (i + T[i] > N + 1) {
                // 오늘 시작되는 상담 했을 때, 퇴사일까지 끝나지 않는 경우
                // 상담이 끝나는 날의 범위를 초과하면 선택 x
                arr[i] = arr[i+1];
            } else {
                // 상담 선택하거나 선택하지 않은 경우 최대값
                // 오늘 시작되는 상담을 했을 때, 퇴사일 안에 끝나는 경우
                arr[i] = Math.max(arr[i+1], P[i] + arr[i + T[i]]);
            }
        }

        // 첫날때부터 시작했을 때의 최대 수익
        System.out.println(arr[1]);
    }
}
