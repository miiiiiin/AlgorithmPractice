package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10986 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long answer = 0;

        // 합배열
        long[] sum = new long[n];
        // 나머지 배열
        long[] count = new long[m];

        st = new StringTokenizer(br.readLine());
        sum[0] = Integer.parseInt(st.nextToken());

        for (int i=1; i<n; i++) {
            // 합 배열 만들기
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
            System.out.println(Arrays.toString(sum));
        }

        for (int i=0; i<n; i++) {
            // 나머지 값 계산
            int remainder = (int) (sum[i] % m);
            System.out.println("remainder = " + remainder);
            // 0~i까지의 구간 합이 0일 때 정답 1 증가
            if (remainder == 0) answer++;
            // 나머지가 같은 인덱스의 개수 카운팅
            count[remainder]++;

//            System.out.println("answer = " + answer);
            System.out.println("count = " + Arrays.toString(count));
        }


        // 나머지 값 배열의 원소 값이 0이면 원본 배열의 0부터 i까지의 구간 합이 이미 M으로 나누어 떨어진다는 뜻.
        for (int i=0; i<m; i++) {
            // 나머지가 같은 인덱스(count[0], count[1]) 중 2개를 뽑는 경우의 수 더함
            if (count[i] > 1)
                System.out.println(i + " index = " + count[i] + ", " + (count[i] - 1));
                // 나머지가 같은 인덱스 중 2개를 뽑는 경우의 수 구하기
                long random = (count[i] * (count[i]-1)/2);
                answer += random;
        }

        System.out.println(answer);
    }
}
