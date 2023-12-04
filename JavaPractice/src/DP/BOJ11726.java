package DP;

import java.util.Scanner;

// 2*N 타일 채우기
public class BOJ11726 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // DP TABLE 생성 (n이 1000까지이나 그렇게 크지 않아 부담없이 1001까지 만들어둠)
        long D[] = new long[1001];
        // 한 칸 일 때는 세로로 타일 하나 만드는 경우의 수 밖에 없음(초기화: 가장 작은 쉬운 문제 미리 저장)
        // 길이(N) = 1 일때, 타일 채우는 경우의 수
        D[1] = 1;
        // 2칸일 경우, 타일을 세로로 두개 쌓거나, 가로로 두 개 붙이는 경우 밖에 없음.
        D[2] = 2;

        for(int i = 3; i<=N; i++) {
            D[i] = (D[i-1] + D[i-2]) % 10007;
            // 나온 결과를 10007 나머지 연산 수행 (DP 테이블 채워짐)
//            System.out.println(D[i]);
//            System.out.println(N);
        }
        System.out.println(D[N]);
    }
}

