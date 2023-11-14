package DP;

import java.util.Arrays;
import java.util.Scanner;

// 백준 p2747
// 재귀함수 형태로 구현되어있기 때문에 재귀의 깊이가 매우 깊어질 경우 런타임 에러가 발생할 수 있음
public class TopDown1 {
    static int[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        D = new int[n + 1];

        for (int i=0; i <= n; i++) { // DP 테이블 -1로 초기화
            D[i] = -1;

        }

        System.out.println(Arrays.toString(D));

        // 가장 작은 문제(이미 아는 값) -> 초기화
        D[0] = 0;
        D[1] = 1;
        fibo(n);
        System.out.println(D[n]);

        System.out.println(Arrays.toString(D));
    }

    static int fibo(int n) {
        if (D[n] != -1) // 기존에 계산한 적이 있는 부분의 문제는 재계산하지 않고 리
            return D[n];

        return D[n] = fibo(n-2) + fibo(n-1);
        // 메모이제이션: 구한 값을 바로 리턴하지 않고 DP 테이블에 저장한 후 리턴하도록 로직 구현

    }
}
