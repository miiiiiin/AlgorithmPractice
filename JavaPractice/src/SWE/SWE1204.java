package SWE;

import java.util.Scanner;

public class SWE1204 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        int students = 1000;
        int maximumScore = 100;

        for(int test_case = 1; test_case <= T; test_case++) {
            sc.nextInt();
            int[] scores = new int[101];
            for (int i=0; i<students; i++) {
                // 1000명의 학생 점수 입력
                // 점수별 등장 횟수 저장
                scores[sc.nextInt()]++;
            }
            // 최빈수 값과 최빈값인 점수 저장
            int max = 0, score = 0;
            // 큰 점수부터
            for (int i=maximumScore; i>0; i--) {
                if (scores[i] > max) {
                    max = scores[i];
                    score = i;
                }
            }
            System.out.printf("#%d %d\n", test_case, score);
        }
    }
}
