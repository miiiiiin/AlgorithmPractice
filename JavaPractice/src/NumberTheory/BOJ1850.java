package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1850 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long result = gcd(A, B);

        // result 수만큼 1 출력
        while (result > 0) {
            sb.append(1);
            result--;
        }

        br.close();
        System.out.println(sb);
    }

    // 최대 공약수 구하는 함수

    /**
     * 유클리드 호제법
     * 1. 큰 수를 작은 수로 나누는 mod 연산 수행
     * 2. 1번 단계에서의 작은 수와 mod 연산 결과값(나머지)로 mod 연산 수행
     * 3. 2번 단계 반복하다가 나머지가 0이 되는 순간의 작은 수가 최대 공약수
     */
    public static Long gcd(long a, long b) {
        if (b==0) return a;
        else return gcd(b, a%b);
    }
}
