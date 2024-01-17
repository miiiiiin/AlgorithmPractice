package Greedy;

import java.util.Scanner;

public class BOJ11047 {
    static int[] arr;
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[n];

        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }

        // greedy
        // 최대한 큰 동전 먼저 쓰기

        int count = 0;

        // 내림차순 탐색 (큰 동전부터 탐색)
        for (int i=n-1; i>=0; i--) {
            if (a[i] <= k) {
                // 사용한 동전 수
                count += (k/a[i]);
                // k의 값을 사용한 동전의 나머지로 갱신
                k = k % a[i];
            }
        }
        System.out.println(count);
    }
}
