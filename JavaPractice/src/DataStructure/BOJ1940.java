package DataStructure;

import java.util.Arrays;
import java.util.Scanner;
public class BOJ1940 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 재료 개수
        int n = sc.nextInt();
        // 갑옷이 완성되는 고유번호의 합
        int m = sc.nextInt();
        // 재료 번호 합쳐서 m이 되는 경우의 개수
        int count = 0;

        // n개의 재료들이 가진 번호들의 배열
        int[] numbers = new int[n];

        for (int i=0; i<n; i++) {
            numbers[i] = sc.nextInt();
        }

        // 합계 연산 편의성을 위해 오름차순 정렬
        Arrays.sort(numbers);

        // 투 포인터 시작 인덱스
        int s_idx = 0;
        // 종료 인덱스
        int e_idx = n - 1;

        // 배열의 양 쪽 끝단에서 시작하여 중심으로 인덱스를 이동시키며 합을 구함
        while (s_idx < e_idx) {
            // 양 쪽 포인터가 가리키는 배열값의 합
            int sum = numbers[s_idx] + numbers[e_idx];
            if (sum == m) {
                // 합계가 m과 같으면 양쪽 포인터 중심 방향으로 이동시키고, 개수 카운트 증가
                count++;
                s_idx++;
                e_idx--;
            } else if (sum > m) {
                e_idx--;
            } else {
                // 합계가 m보다 작으면
                s_idx++;
            }
        }
        System.out.println(count);
    }
}
