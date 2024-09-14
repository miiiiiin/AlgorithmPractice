package Sorting;

import java.util.Scanner;

public class MergeSortBottomUp {

    // 합치는 과정에서 정렬하여 원소르 담을 임시배열
    static int[] sorted;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[5];

        for (int i=0; i<a.length; i++) {
            a[i] = sc.nextInt();
        }

        sorted = new int[a.length];
        merge_sort(a, 0, a.length-1);
        sorted = null;
    }

    // Bottom up
    // 부분리스트로 나누는 과정만 Bottom-Up 방식으로 변경
    // 대부분의 경우 정렬 과정은 최대한 재귀는 피하여 구현하는게 일반적이기 때문에 Bottom-Up 으로 구현하는 것이 좋음.
    static void merge_sort(int[] a, int left, int right) {
        /*
         * 1 - 2 - 4 - 8 - ... 식으로 1부터 서브리스트를 나누는 기준을 두 배씩 늘린다.
         */
        for (int size =1; size <= right; size += size) {
            /**
             * 두 부분리스트를 순서대로 병합
             * 현재 부분리스트의 크기가 1(size=1) 일 때
             * 왼쪽 부분리스트 (low ~ mid)와 오른쪽 부분 리스트 (mid + 1 ~ high)를 생각하면
             * 왼쪽 부분리스트 low = mid = 0이고,
             * 오른쪽 부분리스트는 mid + 1부터 low + (2 * size) - 1 = 1이 됨.
             *
             * 이 때 high가 배열의 인덱스를 넘어갈 수 있으므로 right과 둘 중 작은 값이 병합되도록 해야 함.
             */

            for (int l = 0; l<= right - size; l += (2 * size)) {
                int low = l;
                int mid = l + size - 1;
                int high = Math.min(l + (2 * size) - 1, right);
                merge(a, low, mid, high);
            }
        }
    }

    /**
     * 합칠 부분리스트는 a 배열의 left ~ right 까지이다.
     * @param a
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] a, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (a[l] <= a[r]) {
                sorted[idx] = a[l];
                idx++;
                l++;
            } else {
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        }

        if (l > mid) {
            while (r <= right) {
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        } else {
            while (l <= mid) {
                sorted[idx] = a[l];
                idx++;
                l++;
            }

            for (int i=left; i<=right; i++) {
                a[i] = sorted[i];
            }
        }
    }
}
