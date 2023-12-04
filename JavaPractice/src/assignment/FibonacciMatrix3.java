package assignment;
import java.util.Scanner;

public class FibonacciMatrix3 {


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
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        // 행렬 A 초기화
        long[][] matrixA = {{1, 1}, {1, 0}};
        long[][] resultMatrix = matrixPower(matrixA, n - 1);

        // 결과 출력
        System.out.println(resultMatrix[0][0]);
    }
}


