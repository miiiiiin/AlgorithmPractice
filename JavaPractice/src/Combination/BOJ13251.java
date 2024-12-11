package Combination;

import java.util.Scanner;

public class BOJ13251 {
    static int[] stones;
    static int[][] D;
    // 각 집합에 대한 확률 배열
    static double[] probability;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 조약돌의 색상 1~M까지 (첫째 줄에 M (1 ≤ M ≤ 50)
        int M = sc.nextInt();
        // 각 색상의 조약돌 개수 저장하는 배열 (ex : 5개의 조약돌이 있는 색깔)
        stones = new int[51];
        probability = new double[51];
        // 총 원소 개수
        int T = 0;
        double answer = 0.0;

        for (int i=0; i<M; i++) {
            stones[i] = sc.nextInt();
            T += stones[i];
        }

        // 랜덤하게 뽑는 조약돌 개수
        int K = sc.nextInt();

        for (int i=0; i<M; i++) {
            // 현재 집합 크기가 K 이상일 경우
            if (stones[i] > K) {
                // 초기화
                probability[i] = 1.0;
            }

            for (int k=0; k<K; k++) {
                // 확률 계산
                // 색깔별 조약돌의 개수에서 k를 뽑을 수 있는 경우의 수 / 전체 돌에 관해 k개를 뽑는 경우의 수
                // i 색깔을 모두 뽑을 확률 = i 색깔을 모두 뽑을 확률 * 현재 색깔 개수 - k / 전체 색깔 개수 - k
                probability[i] *= (double) (stones[i] - k) / (T - k);
            }
            answer += probability[i];
        }

        System.out.println(answer);
    }
}
