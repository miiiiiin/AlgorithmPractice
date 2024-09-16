package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// swap 횟수 출력 (버블 정렬)
public class BOJ1517 {
    static int[] arr, tmp;
    // index가 이동한 거리 저장
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수의 개수
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        // 정렬 저장할 입시 배열
        tmp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        merge_sort(0, n-1);
        System.out.println(result);
    }

    public static void merge_sort(int start, int end) {
        if (start == end) return;
        int mid = (start + end) / 2;
        merge_sort(start, mid);
        merge_sort(mid+1, end);
        // 병합
        merge(start, mid, end);
    }

    public static void merge(int start, int mid, int end) {
        int idx = start;
        int l = start;
        int r = mid+1;

        while (l <= mid && r <= end) {
            if (arr[l] <= arr[r]) {
                tmp[idx++] = arr[l++];
            } else {
                // 뒤 쪽 데이터 값이 더 작아 선택될 때 swap이 일어났다고 가정
                // 현재 남아 있는 앞쪽 데이터 개수만큼 결과값 더함
                tmp[idx++] = arr[r++];
//                System.out.println("idx = " + idx + ", r = " + r);
                result += (r - idx);
//                System.out.println("result = " + result);
            }
        }

        // 남아있는 데이터 정리
        while (l <= mid) {
            tmp[idx++] = arr[l++];
        }

        while (r <= end) {
            tmp[idx++] = arr[r++];
        }

        for (int i=start; i<=end; i++) {
            arr[i] = tmp[i];
//            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }
}
