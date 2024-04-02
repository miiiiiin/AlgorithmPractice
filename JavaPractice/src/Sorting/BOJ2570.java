package Sorting;

import java.util.Scanner;

// n^2에 대한 정렬 알고리즘 -> 버블 정렬
public class BOJ2570 {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i=0; i<n-1; i++) {
            // 루프 한 번 돌수록 정렬된 부분이 생김 (정렬 범위가 좁아지므로 i를 빼줌으로써 정렬 범위를 맞춤)
            for (int j=0; j<n-1-i; j++) { // 하나의 루프에서 정렬 범위
                // 현재 배열의 보다 1칸 오른쪽 배열의 값이 더 작으면 두 수 자리 스왑
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i=0; i<n; i++) {
            System.out.println(arr[i]);
        }
    }
}
