package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 표의 크기 n
        int n = Integer.parseInt(st.nextToken());
        // 합을 구해야하는 횟수 m
        int m = Integer.parseInt(st.nextToken());

        // 원본 배열
        long[][] arr = new long[n+1][n+1];
        // 합배열
        long[][] sum = new long[n+1][n+1];

        // 원본 배열 저장
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
//                System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr[i]));
//                System.out.println("i = " + i + ", j = " + j);
            }
        }

        // 합 배열 저장 (합 배열 채우기)
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                // 구간 합
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
//                System.out.println("sum = " + sum[i][j]);
            }
        }

        for (int k=0; k<m; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력
            long result = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
            System.out.println(result);
        }
    }
}
