package Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSortTopDown {

    static int[] sorted;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[5];

        for (int i=0; i<a.length; i++) {
            a[i] = sc.nextInt();
        }
        sorted = new int[a.length];
        merge_sort(a, 0, a.length - 1);

    }

    // TOP DOWN 방식 구현 (부분 원소로 쪼개는 부분)
    public static void merge_sort(int[] a, int left, int right) {
        /*
        left == right의 경우, 부분 리스트가 1개인 원소만 갖고 있는 경우
        더 이상 쪼갤 수 없으므로 return
         */
        System.out.println("left = " + left + ", right = " + right);
        if (left == right) return;

        // 부분으로 나누기
        int mid = (left + right) / 2;
        System.out.println("mid = " + mid);

        // 재귀
        merge_sort(a, left, mid);
        merge_sort(a, mid + 1, right);

        // 병합
        merge(a, left, mid, right);

        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
    }

    /**
     *
     * @param a
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] a, int left, int mid, int right) {
       int l = left;
       int r = mid + 1;
       int idx = left;

       while (l <= mid && r <= right) {
           /**
            * 왼쪽 부분 리스트 l번째 원소가 오른쪽 부분리스트 r번째 원소보다 작거나 같을 경우
            * 왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1씩 증가시킴
            */

           System.out.println("a[l] = " + a[l] + ", a[r] = " + a[r]);
           if (a[l] <= a[r]) {
               sorted[idx] = a[l];
               idx++;
               l++;
               System.out.println("sorted a[l] < a[r] = " + Arrays.toString(sorted));
           } else {
               /**
                * 오른쪽 부분리스트 r번째 원소가 왼쪽 부분리스트 l번째 원소보다 작거나 같을 경우
                * 오른쪽의 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가시킨다.
                */

               sorted[idx] = a[r];
               idx++;
               r++;

               System.out.println("sorted a[l] >= a[r] = " + Arrays.toString(sorted));
           }
       }
        /**
         * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (l > mid) = 오른쪽 부분리스트 원소가 아직 남아있을 경우
         * 오른쪽 부분리스트의 나머지 우너소들을 새 배열에 채워줌.
         * 오른쪽 배열 남은 경우
         */
        System.out.println("l = " + l + ", mid = " + mid);
//        if (l > mid) {
            while (r <= right) {
                sorted[idx] = a[r];
                idx++;
                r++;

                System.out.println("sorted r <= right = " + Arrays.toString(sorted));
            }
//        } else {

            /**
             * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (r > right)
             * 왼쪽 부분리스트 원소가 아직 남아있을 경우
             * 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워줌.
             * 왼쪽 배열 남은 경우
             */

            while (l <= mid) {
                sorted[idx] = a[l];
                idx++;
                l++;

                System.out.println("sorted l <= mid = " + Arrays.toString(sorted));
            }
//        }

        /**
         * 정렬된 새 배열을 기존의 배열에 복사하여 옮겨줌
         */
        for (int i=left; i<=right; i++) {
            a[i] = sorted[i];
            System.out.println("================a = " + Arrays.toString(a));
        }

    }
}
