package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sc = new StringTokenizer(br.readLine());

        // 데이터 개수
        int n = Integer.parseInt(sc.nextToken());
        // k번째에 있는 수
        int k = Integer.parseInt(sc.nextToken());

        int[] numArr = new int[n];

        sc = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(sc.nextToken());
        }

        // 퀵 정렬
        quickSort(numArr, 0, n-1, k-1);
        System.out.println(numArr[k-1]);
    }

    public static void quickSort(int[] numArr, int start, int end, int k) {
        if (start < end) {
            int pivot = pivoting(numArr, start, end);

            // k번째 수가 pivot이면 더 이상 구할 필요x
            if (pivot == k) {
                return;
            } else if (k < pivot)
                // k가 pivot 보다 작으면 왼쪽 그룹만 정렬 수행
                quickSort(numArr, start, pivot - 1, k);
            else
                // k가 pivot보다 크면 오른쪽 그룹만 정렬 수행
                quickSort(numArr, pivot+1, end, k);
        }
    }

    // 피봇 지정 함수
    public static int pivoting(int[] numArr, int start, int end) {
        // start가 가리키는 데이터가 pivot이 가리키는 데이터보다 작으면 start 오른쪽으로 1씩 이동
        // end가 가리키는 데이터가 pivot이 가리키는 데이터보다 크면 end 왼쪽 이동

        if (start + 1 == end) {
            if (numArr[start] > numArr[end]) {
                swap(numArr, start, end);
            }
        }

            // 중간값
            int mid = (start + end) / 2;
            // 중앙값을 1번째 요소로 이동
            swap(numArr, start, mid);

            int pivot = numArr[start];
            int i = start + 1;
            int j = end;

            System.out.println("pivot = " + pivot + ", i = " + i + ", j =" + j);


        // start가 가리키는 데이터가 pivot이 가리키는 데이터보다 크고, end가 가리키는 데이터가 pivot이 가리키는 데이터보다
        // 작으면 start, end가 가리키는 데이터를 swap
        // start 오른쪽, end 왼쪽으로 1씩 이동
        // start와 end 만날때까지 반복


            // 포인터 i와 j가 같아질때까지
            while (i <= j) {
                System.out.println("i = " + i + ", j = " + j + ", start = " + (start+1) + ", pivot = " + pivot + ", numj = " + numArr[j]);
                // j가 피벗보다 크면 j--
                // j가 피벗보다 작은 수가 나올 때까지 j--
                // j가 start+1보다 크고, 피벗이 배열의 맨 마지막 요소보다 작은 동안
                while (j >= start + 1 && pivot < numArr[j]) {
                    j--;
                    // j는 i에 위치하게 됨
                }

                // i가 피벗보다 작으면서 i보다 j가 크면 i++ 연산 반복
                // i와 j의 위치가 같을 경우, i는 이동하지 않음.
                // 피벗보다 큰 수가 나올때까지 i++;
                while (i <= end && pivot > numArr[i]) {
                    i++;
                }

                if (i <= j) {
                    swap(numArr, i++, j--);
                }
            }

            // start와 end가 만나면 만난 지점에서 가리키는 데이터와 pivot이 가리키는 데이터를 비교
            // pivot이 가리키는 데이터가 크면 만난 지점 오른쪽,
            // 작으면 만난 지점의 왼쪽에 pivot이 가리키는 데이터를 삽입

            // 피벗 데이터를 나눠진 두 그룹의 경계 index에 저장
//             System.out.println("numArr start = " + numArr[start] + ", numarr j =" + numArr[j] + ", pivot = " + pivot);
            numArr[start] = numArr[j];
            numArr[j] = pivot;
            return j;

            // 분리 집합이 1이 될때까지 각각 분리 집합에서 다시 pivot 계속 선정
        }

        public static void swap(int[] numArr, int i, int j) {
            int temp = numArr[i];
            numArr[i] = numArr[j];
            numArr[j] = temp;
        }
}
