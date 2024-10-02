package NumberTheory;

import java.util.Scanner;

public class BOJ1456 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        int count = 0;

        // 1 ≤ A ≤ B ≤ 10^14 (제곱근인 10^7까지 소수 탐색해야 함)
        long[] arr = new long[10000001];

        for (int i=2; i < arr.length; i++) {
            arr[i] = i;
        }

        // 에라토스테네스 체 이용해 소수 구함 (제곱근까지 수행)
        for (int i=2; i <= Math.sqrt(arr.length); i++) {
            if (arr[i] == 0)
                continue;
            for (int j=i+i; j < arr.length; j=j+i) {
                // 배수 지우기(해당 수가 소수가 아님을 표시)
                arr[j] = 0;
            }
        }

        for (int i=2; i < 10000001; i++) {
            // 소수인 경우
            if (arr[i] != 0) {
                long temp = arr[i];
                // temp <= b / temp는 temp * temp <= b와 같다.
                // temp 나눠주는 이항처리 (소수 제곱근 곱했을 시 long의 범위 넘을 수도 있어서)

                // 소수 N제곱 한 값(temp)이 B보다 커질 때까지 반복문 실행
                double whileCheck = (double)b / (double)temp;
                double nearyPrime = (double)a / (double)temp;
                while ((double)arr[i] <= (double)b / (double)temp) {
                    if ((double)arr[i] >= (double)a / (double)temp) {
                        // 소수 N제곱 한 값(temp)이 A보다 크거나 같으면 '거의 소수'이므로 카운팅
                        count++;
                    }
                    temp = temp * arr[i];
                }
            }
        }

        // A보다 크거나 같고, B보다 작거나 같은 거의 소수가 몇 개인지 출력
        System.out.println(count);
    }
}
