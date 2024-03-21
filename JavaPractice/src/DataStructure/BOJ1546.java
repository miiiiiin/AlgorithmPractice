package DataStructure;

import java.util.Scanner;

public class BOJ1546 {

    static long sum, max;
    // 자기 점수 중에 최댓값 m
    // 점수/M*100

    // 예시
    // 3
    // 40 80 60

    // ((40/80*100) + (80/80*100) + (60/80*100))/3 = 75
    // 식 간소화 => ((40+80+60)/80)*100/3 = 75
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i=0; i<n; i++) {
            int tmp = sc.nextInt();
            if (tmp > max) max = tmp;
            sum += tmp;
        }
        System.out.println(sum * 100.0/max/n);
    }
}

