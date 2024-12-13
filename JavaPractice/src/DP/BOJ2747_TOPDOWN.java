package DP;

import java.util.Scanner;

// 재귀함수 형태로 구현되어있기 때문에 재귀의 깊이가 매우 깊어질 경우 런타임 에러가 발생할 수 있음
public class BOJ2747_TOPDOWN {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        arr = new int[N+1];

        // 초기화
        for (int i=0; i<=N; i++) {
            arr[i] = -1;
        }

        arr[0] = 0;
        arr[1] = 1;

        fibonacci(N);
        System.out.println(arr[N]);
    }

    static int fibonacci(int n) {
        // 이전에 계산 완료된 부분은 재계산하지 않고 리턴
        if (arr[n] != -1) {
            return arr[n];
        }
        // 메모이제이션: 구한 값을 바로 리턴하지 않고 DP 테이블에 저장한 후 리턴하도록 로직 구현
        return arr[n] = fibonacci(n-1) + fibonacci(n-2);
    }
}
