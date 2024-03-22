package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659 {

    static int[] numbers;
    static int[] sumArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        // 합 구해야하는 횟수
        int m = Integer.parseInt(st.nextToken());

        // 덧셈이나 곱셈이 많을 때 int 형 수 범위를 넘어가는 경우가 많아서 long형으로 선언
        // 합배열
        long[] sum = new long[n+1];
        numbers = new int[n];
        sumArr = new int[n];

        st = new StringTokenizer(br.readLine());
        // n 개의 수
        for (int i=1; i<=n; i++) {
            // 합배열
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
//            System.out.println(Arrays.toString(sum));
        }

        // 합을 구해야하는 i, j (m개 줄)
        for (int k=0; k<m; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

//            System.out.println("sumarr:" + Arrays.toString(sum) + " " + sum[j] + " " + sum[i-1]);
            System.out.println(sum[j] - sum[i-1]);
        }
    }
}
