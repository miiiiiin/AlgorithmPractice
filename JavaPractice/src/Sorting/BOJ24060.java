package Sorting;

import java.util.Arrays;
import java.util.Scanner;

// 배열 A에 K 번째 저장되는 수를 구하는 문제
public class BOJ24060 {

    static int[] arr, tmp;
    // 저장 횟수 누적 카운트
    static int count = 0;
    // 결과 (실패 시 -1 출력)
    static int result = -1;
    // 저장 횟수
    static int k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 배열 개수
        int n = sc.nextInt();
        // 저장 횟수
        k = sc.nextInt();
        // 배열
        arr = new int[n];
        // 임시 배열
        tmp = new int[n];

        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        // 최초 시작점 0, 마지막 인덱스(n-1): 배열은 0부터 시작하기 때문에
        merge_sort(0, n-1);
        System.out.println(result);
    }

    public static void merge_sort(int start, int end) {
        if (start == end) return;

        int mid = (start + end) / 2;
        // 전반부
        merge_sort(start, mid);
        // 후반부
        merge_sort(mid+1, end);

        // 병합
        merge(start, mid, end);
    }

    // 나머지 부분 리스트 병합

    /**
     *
     * @param start
     * @param mid 중간 (쪼갠 배열의 첫번째 지점)
     * @param end
     */
    public static void merge(int start, int mid, int end) {
        int idx = start;
        // 2가지 그룹으로 병합 이후, 포인터 개념 사용하여 두 그룹 병합
        int l = start;
        int r = mid+1;

        //시작 인덱스가 중간 인덱스(1번쨰)보다 작고,
        //중간인덱스(2번째)가 마지막인덱스보다 작을 경우 반복
        /**
         * 시작 인덱스가 mid(중간 1번째 인덱스)보다 작고,
         * mid 인덱스(2번째)가 end 인덱스보다 작을 경우 반복
         *
         * 예시
         *  ^            ^
         * [0, 1, 2(mid) 3, 4, 5(end)]
         */
        while (l <= mid && r <= end) {
            if (arr[l] <= arr[r]) {
                // 앞의 값이 뒤의 값보다 작을 경우, 작은값을 넣어줌.
                tmp[idx++] = arr[l++];
            } else {
                // 앞의 값이 뒤의 값보다 클 경우, 작은값을 넣어줌.
                tmp[idx++] = arr[r++];
            }
        }

        // 다 정렬하고 남은 경우, 남아있는 값 정리하기
        // 왼쪽 배열 남은 경우
        while (l <= mid) {
            tmp[idx++] = arr[l++];
            System.out.println("sorted l <= mid = " + Arrays.toString(tmp));
        }

        // 오른쪽 배열 남은 경우
        while (r <= end) {
            tmp[idx++] = arr[r++];
            System.out.println("sorted r <= right = " + Arrays.toString(tmp));
        }

        // 정렬을 끝낸 임시 배열을 순서대로 정렬된 값을 arr에다 복사함.
        for (int i=start; i<=end; i++) {
            count++;
            arr[i] = tmp[i];

            if (count == k) {
                // 저장 횟수가 같다면 증가한만큼의 t 인덱스 값을 결과값에 저장
                result = tmp[i];
                break;
            }
//            System.out.println("================arr = " + Arrays.toString(arr));
        }
    }
}
