package BinarySearch;

import java.util.Scanner;

public class BOJ2343 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 강의 갯수
        int n = sc.nextInt();
        // 블루레이 갯수
        int m = sc.nextInt();

        // start, end 인덱스
        int start = 0;
        int end = 0;

        int[] arr = new int[n];

        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            // 시작 인덱스 저장 (배열 중 최대값)
            if (arr[i] > start) start = arr[i];
            // 종료 인덱스 저장 (배열 총합)
            end += arr[i];
        }

        // 가능한 블루레이 크기 중 최소값 찾아야 함
        // 이진 탐색
        while(start <= end) {
            // 블루레이 크기라고 가정
            int mid = (start + end) / 2;
            // 레슨 합
            int sum = 0;
            // 현재 사용한 블루레이 개수
            int count = 0;

            // mid 값으로 블루레이에 레슨 저장할 수 있는지 확인
            for (int i=0; i<n; i++) {
                // 현재 블루레이 크기를 초과하기에 저장할 수 없어 새로운 블루레이를 추가함
                if (sum + arr[i] > mid) {
                    count++;
                    sum = 0;
                }
                sum += arr[i];
            }

            // sum이 남을 경우, 그 길이만큼 담을 블루레이가 하나 더 필요함
            if (sum != 0) count++;
            // mid 크기로 블루레이 3장 이내에 모든 레슨 저장할 수 없으면
            if (count > m) {
                start = mid+1;
            } else {
                // mid 크기로 블루레이 3장 이내에 모든 레슨 저장할 수 있으면
                end = mid-1;
            }
        }
        System.out.println(start);
    }
}
