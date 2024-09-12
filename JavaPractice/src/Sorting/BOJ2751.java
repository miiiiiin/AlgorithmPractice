package Sorting;

// N(1 ≤ N ≤ 1,000,000)
// n 숫자의 범위가 크므로 O(NlogN) 시간복잡도의 알고리즘이 적합 (병합정렬)

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class BOJ2751 {
    public static int[] arr, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        // 합치는 과정에서 정렬하여 원소를 담을 임시배열
        tmp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        merget_sort(1, n); // 병합정렬 수행하기
        for (int i = 1; i <= n; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void merget_sort(int start, int end) {
        // 1. 분할
        if (end - start < 1)
            return;

        int mid = start + (end - start) / 2;

        // 재귀함수 형태로 구현
        merget_sort(start, mid);
        merget_sort(mid + 1, end);

        for (int i = start; i <= end; i++) {
            tmp[i] = arr[i];
        }

        // 2. 정복
        int k = start;
        // 2가지 그룹으로 병합 이후, 포인터 개념 사용하여 두 그룹 병합
        int index1 = start;
        int index2 = mid + 1;

        while (index1 <= mid && index2 <= end) { // 두 그룹을 Merge 해주는 로직
            // 두 그룹 병합
            // 양쪽 그룹의 index가 가리키는 값을 비교하여 더 작은 수를 선택해 배열에 저장
            // 선택된 데이터의 index 값을 오른쪽으로 한 칸 이동
            if (tmp[index1] > tmp[index2]) {
                arr[k] = tmp[index2];
                k++;
                index2++;
            } else {
                arr[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        // 한쪽 그룹이 모두 선택된 후 남아있는 값 정리하기
        while (index1 <= mid) {
            arr[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= end) {
            arr[k] = tmp[index2];
            k++;
            index2++;
        }

    }
}
// ARR: 3 4 5 /(MID) 1 2
