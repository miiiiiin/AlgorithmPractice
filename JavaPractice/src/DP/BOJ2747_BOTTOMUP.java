package DP;

import java.util.Scanner;

public class BOJ2747_BOTTOMUP {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        arr = new int[N+1];

        for (int i=0; i<=N; i++) {
            arr[i] = -1;
        }

        arr[0] = 0;
        arr[1] = 1;

        for (int i=2; i<=N; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        System.out.println(arr[N]);
    }
}
