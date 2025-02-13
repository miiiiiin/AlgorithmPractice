package SWE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SWE1206 {

    // 건물 개수 (가로길이)
    static int N;
    static int[] buildings;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            buildings = new int[N];

            for (int i=0; i<N; i++) {
                buildings[i] = sc.nextInt();
            }

            // 조망권 확보 세대 수
            int result = 0;
            // 맨 왼쪽 두 칸과 맨 오른쪽 두 칸에는 건물이 지어지지 않는다.
            for (int i=2; i<N-2; i++) {
                int left = Math.max(buildings[i-1], buildings[i-2]);
                int right = Math.max(buildings[i+1], buildings[i+2]);
                int sub = Math.max(left, right);
                if (buildings[i] > sub) {
                    result += (buildings[i] - sub);
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}
