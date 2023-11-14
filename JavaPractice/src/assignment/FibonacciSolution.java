package assignment;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

//재귀 호출:
//        피보나치 수열의 n번째 항은 (n-1)번째 항과 (n-2)번째 항의 합입니다. 따라서 다음과 같은 재귀적인 함수를 작성합니다.
//        fib(n) = fib(n-1) + fib(n-2)

//기본 사례를 기반으로 재귀적으로 피보나치 수를 계산합니다.
//        fib(2) = fib(1) + fib(0) = 1 + 0 = 1
//        fib(3) = fib(2) + fib(1) = 1 + 1 = 2
//        fib(4) = fib(3) + fib(2) = 2 + 1 = 3


public class FibonacciSolution {
    // 분할정복 방법
    public static int fibRemainder(int n) {
        if (n <= 1) {
            return n; // 기본 사례: F(0) = 0, F(1) = 1
        } else {
            // 재귀 호출: F(n) = F(n-1) + F(n-2)
            return fibRemainder(n - 1) + fibRemainder(n -2);
        }
    }


    // 재귀식 방법
    public static int findNthFibonacci(int n) {
        // fib 배열은 계산된 피보나치 수를 저장하는 데 사용
        // 배열 크기를 n + 2로 설정한 이유는 0부터 n까지 계산하기 위함
        int[] fib = new int[(int) n + 2];

        // 초기값
        fib[0] = 0;
        fib[1] = 1;

        // 반복적으로 피보나치 수 계산
        for (int i = 2; i <= n; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % 1_000_000; // 나머지 연산
            // fib[i - 1]과 fib[i - 2]를 더한 후 1,000,000으로 나눈 나머지를 fib[i]에 저장합니다.
            // 이는 피보나치 수열의 주기성과 힌트에서 언급한 것처럼 나머지 연산을 사용함을 의미
        }
        return fib[(int) n];
//        반복문이 완료되면 fib[(int) n]에 n번째 피보나치 수가 저장되며, 이 값을 반환합
    }


    public static long fibonacci(long n) {
        if (n <= 1) {
            return n % 1_000_000;
        } else {
            // 재귀 호출: F(n) = F(n-1) + F(n-2)
            long result = fibonacci(n - 1) + fibonacci(n -2);
            return result % 1_000_000;
        }
    }


    public static void main(String[] args) {
//        int n = 10;
//        int result = findNthFibonacci(n);
//        System.out.println("F(" + n + ") % 1,000,000 = " + result);
//
//
//        result = fibRemainder(n);
//        System.out.println("F(" + n + ") = " + result);
        long n = 10;
        long result = fibonacci(n);
        System.out.println(result);
    }
}
