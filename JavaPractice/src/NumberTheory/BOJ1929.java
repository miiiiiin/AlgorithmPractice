package NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

// 에라토스테네스의 체 원리 적용
public class BOJ1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n+1];

        for (int i=2; i<=n; i++) {
            arr[i] = i;
        }

        for (int i=2; i <= Math.sqrt(n); i++) {
            if (arr[i] == 0)
                continue;
            for (int j=i+i; j<=n; j=j+i) { // 배수 지우기
                arr[j] = 0;
            }
//            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
        for (int i=m; i<=n; i++) {
            if (arr[i] != 0) {
                System.out.println(arr[i]);
            }
        }
    }
}
