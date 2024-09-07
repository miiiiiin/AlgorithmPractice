package Sorting;

import java.util.Scanner;

public class BOJ2750 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i=0; i<n; i++) {
            numbers[i] = sc.nextInt();
        }

        for (int i=0; i<n-1; i++) {
            for (int j=0; j<n-1-i; j++) { // ex) 5, 4, 3, 2, 1 순으로 범위를 좁힘.
                // 병합 정렬은 loop 한 번씩 돌 때마다 한 번씩 정렬됨
                if (numbers[j] > numbers[j+1]) {
                    int temp = numbers[j+1];
                    numbers[j+1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        for (int i=0; i<n; i++) {
            System.out.println(numbers[i]);
        }
    }
}
