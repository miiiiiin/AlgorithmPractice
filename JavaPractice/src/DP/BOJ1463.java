package DP;

import java.util.Scanner;

// X가 3으로 나누어 떨어지면, 3으로 나눈다.
// X가 2로 나누어 떨어지면, 2로 나눈다.
// 1을 뺀다.
// 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력

// 정수를 1로 만들기

// 예: 입력 10
// 1. 10 - 1 = 9
// 2. 9 / 3 = 3
// 3. 3 / 3 = 1
// 이렇게 3번에 걸쳐서 1을 만들 수 있다.

public class BOJ1463 {

    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        arr = new int[N+1];
        // arr[1] = 1이 될 떄까지 필요한 연산 횟수 (이미 1이니까 1이 될 때까지 연산 불필요 그래서 arr[1] = 0)
        arr[1] = 0;

        for (int i=2; i<=N; i++) {
            // 1을 빼는 연산
            arr[i] = arr[i-1] + 1;
            // 2로 나누는 연산과 비교하여 최솟값 갱신
            if (i%2 == 0) {
                arr[i] = Math.min(arr[i], arr[i/2] + 1);
            }
            if (i%3 == 0) {
                arr[i] = Math.min(arr[i], arr[i/3] + 1);
            }
        }

        System.out.println(arr[N]);

    }
}
