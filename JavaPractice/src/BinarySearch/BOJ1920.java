package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] a = new int[n];

        for(int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        // 이진 탐색 조건: 정렬
        Arrays.sort(a);

        int m = sc.nextInt();
        for (int i=0; i<m; i++) {
            boolean find = false;
            int target = sc.nextInt();

            // start, end 인덱스
            int start = 0;
            int end = a.length - 1;

            // 더 이상 탐색할 구간이 없을 때까지 실행 (시작 인덱스가 더 작거나 같을때까지 반복)
            while (start <= end) {
                int mid_index = (start + end) / 2;
                int mid_value = a[mid_index]; // 실제 중앙값
                if (mid_value > target) {
                    end = mid_index - 1;
                } else if (mid_value < target) {
                    start = mid_index + 1;
                } else {
                    find = true;
                    break;
                }
            }
            if(find)System.out.println(1);
            else System.out.println(0);
        }
    }
}
