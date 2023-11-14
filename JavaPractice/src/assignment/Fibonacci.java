package assignment;


public class Fibonacci
{
    // 메모이제이션
    static int[] values;
    static int number;

    public Fibonacci(int number) {
        this.number = number;
        values = new int[number];
    }

    // 재귀 호출 방식 수행 (피보나치의 정의 제일 직관적인 구현)
    public int fibonacciRecur(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibonacciRecur(n - 1) + fibonacciRecur(n - 2); // 특정 terminate condition에 리턴 없이 계속 가면 stackoverflow 발생

    }

    // 반복문 이용한 피보나치 수열 풀이법
    public int fibonacciIter(int n) {
        // 0과 1을 제외한 나머지 수에서는 이전값과 그 이전값 2개를 가지고 있어야함.
        int ppre = 0; // n-2에 대한 값 저장 (2기준 -2므로 0)
        int pre = 1; // n-1에 대한 값 저장
        int current = 0;

        if (n == 0) return 0;
        if (n == 1) return 1;

        // 2부터 f(n) = n-1 + n2 점화식이 발생하므로 i의 초기값은 2부터
        for (int i = 2; i <= n; i++) {
            current = pre + ppre;
            // 한 번 더 반복문이 돈다고 했을 때 값 조정해주어야함
            ppre = pre;
            pre = current;
            // 반복문이 돌 때마다 이전값과 그 이전값이 계속 replace되면서 current값 만들어줌. n이 될때까지 (n보다 i가 커지면 리턴)
        }
        return current % 1_000_000;

    }

    // 메모이제이션 방식 (values 배열에 계산되는 값을 넣고, 그것을 가져다 쓰는 방식)
    // dynamic programming과 비슷하나 이전의 history 기억하고 있다가 필요할 때 사용하는 방식
    public int fibonacciMem(int n) {
        values[0] = 0;
        values[1] = 1;

        int result = 0;

        if (n == 0) return values[0];
        if (n == 1) return values[1];

        for (int i = 2; i<=n; i++) {
            result = values[i - 1] + values[i - 2];

            if (values[i] == 0) { // 초기화된 상태일 때
                // i번째 값에는 방금 전의 값을 넣어줌
                values[i] = result;
            }
        }
        return result;
    }

    // 피보나치 매트릭스(행렬)

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci(100);
        // 10번째 피보나치 수열의 값
        int result = fib.fibonacciRecur(10);
        System.out.println(result);

        result = fib.fibonacciIter(10);
        System.out.println(result);

        result = fib.fibonacciMem(10);
        System.out.println(result);
    }
}
