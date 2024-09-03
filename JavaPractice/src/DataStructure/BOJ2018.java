package DataStructure;

import java.util.Scanner;

public class BOJ2018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //  N을 몇 개의 연속된 자연수의 합으로 나타내는 가지 수 출력
        int n = sc.nextInt();

        // n만 뽑는 경우의 수 미리 포함하고 초기화 (n이 10일 때, 10인 경우의 수는 1)
        int count = 1;
        // 시작 인덱스
        int s_idx = 1;
        // 종료 인덱스
        int e_idx = 1;
        // 합
        int sum = 1;

        // sum이 이미 n인 경우를 미리 count 했으므로 종료 인덱스는 n과 같지 않을 동안만 이동해야 함.
        while (e_idx != n) {
            // 자연수의 합계가 n과 같으면
            if (sum == n) {
                count++;
                e_idx++;
                sum = sum + e_idx;
//                System.out.println("count sum==n = " + count);
            } else if (sum > n) {
                sum = sum - s_idx; // 왼쪽에 있는 s_idx를 지우고 시작한다는 뜻 (12345가 있으면 시작 인덱스 1을 지움)
//                System.out.println("result sum = " + sum);
                s_idx++; // 시작 인덱스를 오른쪽으로 이동 (증가)
            } else {
                // sum이 n보다 작을 경우
                e_idx++;
                sum += e_idx;
            }
        }

        System.out.println(count);
    }
}
