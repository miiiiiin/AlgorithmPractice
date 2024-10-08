package NumberTheory;

import java.util.Scanner;

public class BOJ1747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[10000001];

        for (int i=2; i<arr.length; i++) {
            arr[i] = i;
        }

        // 소수 배열에서 n보다 크거나 같으면서 팰린드롬 수인 것을 찾음
        for (int i=2; i <= Math.sqrt(arr.length); i++) {
            if (arr[i] == 0)
                continue;
            for (int j=i+i; j<arr.length; j=j+i) {
                arr[j] = 0; // 배수 지움(소수 아님)
            }
        }

        // 소수 n부터 각 소수들이 팰린드롬 수인지 확인
        int i = n;
        while (true) {
            // 소수만
            if (arr[i] != 0) {
                if (isPalindrome(arr[i])) {
                    // 팰린드롬 수이면 출력
                    System.out.println(arr[i]);
                    break;
                }
            }
            i++;
        }
    }

    // 팰린드롬 판별 함수
    public static boolean isPalindrome(int target) {
        String str = String.valueOf(target);
        char[] charArr = str.toCharArray();

        // 시작 인덱스
        int start = 0;
        // 종료 인덱스
        int end = charArr.length-1;

        // 소수 값 String으로 변환 후, string을 char형의 배열 B로 변환
        // start < end 동안 계속 비교하여 팰린드롬 수인지 판별
        // A[start]와 A[end] 비교하고, 값이 같으면 start++, end-- 수행
        while (start < end) {
            if (charArr[start] != charArr[end]) {
                // 값이 다르면 팰린드롬 수가 아님
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
