package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ1932_DP {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        // 데이터 입력
//        int n = in.nextInt();
//        int a[][] = new int [n][n];
//
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<=i;j++) {
//                a[i][j] = in.nextInt();
//            }
//        }
//
//        // 1번 행부터
//        for(int i=1;i<n;i++) {
//            for(int j=0;j<=i;j++) {
//                System.out.println("arr:" + a[i][j]);
//                // j-1열이 >=0을 만족하면, 이전 행의 자신과 같은열(오른쪽 대각선), 자신-1 열(왼쪽 대각선) 중 큰 값을 더해준다.
//                if(j-1>=0) {
//                    System.out.println("arrcheck:" + a[i][j] + " " + a[i - 1][j] + " " + a[i - 1][j - 1]);
//                    a[i][j] += Math.max(a[i - 1][j], a[i - 1][j - 1]);
//                    // 만족하지 않는 좌,우 값들은 자신과 같은열(왼쪽 또는 오른쪽 대각선)의 값만 더해줄 수 밖에 없다.
//                } else {
//                    a[i][j] += a[i - 1][j];
//                }
//            }
//        }
//
//        int max = Integer.MIN_VALUE;
//
//        // 마지막행에서 취할 수 있는 최댓값을 가져오면 된다.
//        for(int i=0;i<n;i++) {
//            if(max<a[n-1][i])
//                max = a[n-1][i];
//        }
//
//        System.out.println(max);
//        in.close();
//    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n+1][n+1];
        StringTokenizer st = null;
        for(int i=1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x=1;
            while(x<=i && st.hasMoreElements()) {
                int num = Integer.parseInt(st.nextToken());
                // 1번째 열일때, 수직
                if(x==1) {
                    dp[i][x] = dp[i-1][x] +num;
                    System.out.println("x==1:" + num + " " + dp[i][x]);
                // 행/열의 수가 같을 때 (1,1) (2, 2) (3, 3) (4, 4) (
                }else if(x==i) {
                    dp[i][x] = dp[i-1][x-1] +num;
                    System.out.println("x==i:" + num + " " + dp[i][x]);
                }else {
                    // (2,1) (3,2) (4,1) (4,2) (4,3)
                    dp[i][x] = Math.max(dp[i-1][x-1], dp[i-1][x])+num;

                    System.out.println("max:" + num + " " + dp[i][x]);
                }
                x++;
            }
        }

        int max =0;
        for(int num : dp[n]) {
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}
