package assignment;

// 3-2 알고리즘 과제 테스트케이스 통과 사례 time limit 3000ms, 메모리 제한 256mb
// 실제 time : 77ms, 메모리 332.75mb
public class FibonacciSuccess {

    public static int fibonacci(long n) {
        if (n <= 1) {
            return (int) n;
        }

        // 행렬 A 초기화
        long[][] matrixA = {{1, 1}, {1, 0}};
        long[][] resultMatrix = matrixPower(matrixA, n - 1);

        return (int) resultMatrix[0][0] % 1_000_000;
    }

    // 두 행렬을 곱하는 함수
    private static long[][] matrixMultiplication(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        C[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % 1_000_000;
        C[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % 1_000_000;
        C[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % 1_000_000;
        C[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % 1_000_000;
        return C;
    }

    // 행렬을 거듭제곱하는 함수
    private static long[][] matrixPower(long[][] A, long n) {
        if (n <= 1) {
            return A;
        }
        long[][] half = matrixPower(A, n / 2);
        long[][] result = matrixMultiplication(half, half);
        if (n % 2 == 1) {
            result = matrixMultiplication(result, A);
        }
        return result;
    }

    public static void main(String[] args) {
        long n = 10;
        long result = fibonacci(n);
        System.out.println(result);
    }
}
