package assignment;

public class FibonacciMatrix {
    public static long[][] multiply(long[][] A, long[][] B) {
        int rowA = A.length;
        int colA = A[0].length;
        int colB = B[0].length;

        long[][] result = new long[rowA][colB];

        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < colA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static long[][] power(long[][] matrix, long n) {
        int size = matrix.length;
        long[][] result = new long[size][size];

        // Initialize result as the identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        while (n > 0) {
            if (n % 2 == 1) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            n /= 2;
        }

        return result;
    }

    public static long fib(int n) {
        if (n <= 1) {
            return n;
        }

        long[][] baseMatrix = {{1, 1}, {1, 0}};
        long[][] resultMatrix = power(baseMatrix, n - 1);

        return resultMatrix[0][0];
    }

    public static void main(String[] args) {
        int n = 10; // 원하는 피보나치 수열의 항
        long result = fib(n);
        System.out.println("F(" + n + ") = " + result);
    }
}




