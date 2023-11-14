package assignment;
// 알고리즘 과제
// 이 방법은 간단하게 피보나치 수열을 계산할 수 있지만, 계산량이 지수적으로 증가하므로 큰 n에 대해서는 효율적이지 않습니다.
// 효율적인 방법으로 피보나치 수열을 계산하려면 메모이제이션(Memoization) 또는 행렬 거듭제곱(Matrix Exponentiation)과 같은 방법을 사용하는 것이 좋다
public class FibDivideConquer {
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        // 분할과 조합
        int a = fib(n - 1);
        int b = fib(n - 2);
        int result = (a + b)% 1_000_000;
        return result;
    }

    public static void main(String[] args) {

        int n = 10; // 원하는 피보나치 수열의 항
        int result = fib(n);
        System.out.println(result);
    }
}
