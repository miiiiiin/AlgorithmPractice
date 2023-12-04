package assignment;

//피보나치 수열을 빠르게 계산하기 위한 행렬 거듭제곱 알고리즘은 피보나치 수열의 특성을 이용하여 효율적으로 계산하는 방법입니다. 이 알고리즘은 다음과 같이 동작합니다.
//
//        두 개의 행렬 A와 B를 정의합니다.
//
//        A = [1 1]
//        [1 0]
//        B = [F(n) ]
//        [F(n-1)]
//        행렬 A를 N번 거듭제곱합니다. 여기서 N은 원하는 피보나치 수열의 항 수입니다.
//
//        행렬 A를 N번 거듭제곱한 결과인 A^N을 행렬 B에 곱합니다. 결과로 B = A^N * B 가 됩니다.
//
//        B 행렬의 첫 번째 열의 첫 번째 요소는 F(N)이 됩니다.

// 이 코드는 행렬 거듭제곱을 사용하여 피보나치 수열을 빠르게 계산하는 예시입니다. 결과적으로 F(n)이 반환됩니다. 이 방법을 사용하면 더 큰 n에 대해서도 효율적으로 계산

public class FibonacciMatrix2 {

    // 행렬 곱셈 함수
//    matrixMultiplication 함수:
//    이 함수는 두 개의 행렬을 곱하는 작업을 수행합니다.
//    매개변수 A와 B는 각각 행렬입니다.
//    두 행렬의 곱셈을 수행하는 방법은 간단합니다. 행렬 C의 각 요소는 행렬 A의 행과 행렬 B의 열을 조합하여 구해집니다. 이것은 반복문을 사용하여 구현됩니다.
//    먼저, 결과를 저장할 빈 행렬 C를 생성합니다. 그리고 두 번의 반복문을 사용해 행렬 A와 B의 요소들을 조합하여 C의 각 요소를 계산합니다. 또한, 마지막에 이 결과 행렬 C를 반환합니다.
    public static long[][] matrixMultiplication(long[][] A, long[][] B) {
        int iRows = A.length;
        int iCols = A[0].length;
        int jCols = B[0].length;

        long[][] c = new long[iRows][jCols];

        for (int i = 0; i < iRows; i++) {
            for (int j = 0; j < jCols; j++) {
                for (int k = 0; k < iCols; k++) {
                    c[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return c;
    }

    // 행렬거듭제곱 함수
//    matrixPower 함수:
//
//    이 함수는 분할 정복을 사용하여 행렬을 거듭제곱하는 역할을 합니다.
//    A는 거듭제곱할 대상 행렬, n은 거듭제곱할 횟수입니다.
//    n이 1일 때, A를 그대로 반환합니다.
//    n이 짝수인 경우, n/2 횟수의 거듭제곱을 계산하고 이를 곱하여 반환합니다.
//    n이 홀수인 경우, n-1 횟수의 거듭제곱을 계산하고 A와 곱한 후 A를 한 번 더 곱하여 반환합니다.

    // 추가설명:
//    이 함수는 분할 정복 기법을 사용하여 주어진 행렬 A를 거듭제곱합니다.
//    A는 거듭제곱할 대상 행렬이고, n은 거듭제곱할 횟수입니다.
//    이 함수는 재귀적으로 동작합니다. n이 1일 때는 A를 그대로 반환하고, 그렇지 않은 경우에는 n을 절반으로 나눕니다.
//    만약 n이 짝수인 경우, A를 (n/2)번 거듭제곱한 행렬을 구하고 그것을 자신과 다시 곱하여 반환합니다. 이렇게 하면 A를 n번 거듭제곱한 결과를 얻게 됩니다.
//    만약 n이 홀수인 경우, n-1번 거듭제곱한 행렬을 구하고, 그것과 A를 곱하고, 다시 A를 한 번 더 곱합니다. 이렇게 하면 A를 n번 거듭제곱한 결과를 얻게 됩니다.
//    이러한 방식으로 분할 정복을 사용하여 거듭제곱을 구하게 되며, 결과적으로 A를 n번 거듭제곱한 행렬을 반환합니다.
    public static long[][] matrixPower(long[][] A, long n) {
        if (n == 1) {
            return A;
        } else if (n % 2 == 0) {
            long[][] half = matrixPower(A, n/2);
            return matrixMultiplication(half, half);
        } else {
            long [][] half = matrixPower(A, (n - 1) / 2);
            long [][] temp = matrixMultiplication(half, half);
            return matrixMultiplication(temp, A);
        }
    }

//    fib 함수:
//    이 함수는 피보나치 수열의 n번째 항을 반환합니다.
//    matrixA는 다음 행렬을 나타내는 2차원 배열입니다: {{1, 1}, {1, 0}}.
//    resultMatrix에는 matrixPower 함수를 사용하여 matrixA를 (n-1)번 거듭제곱한 결과가 저장됩니다.
//    이렇게 하면 resultMatrix[0][0]에는 F(n-1)이 들어 있게 됩니다.

//    반환값으로 resultMatrix의 첫 번째 행의 첫  번째 요소가 F(n)이 됩니다.
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        long[][] matrixA = {{1, 1}, {1, 0}}; // 2x2 행렬
        long[][] resultMatrix = matrixPower(matrixA, n - 1);
//        return resultMatrix[0][0];
        long result = resultMatrix[0][0] % 1_000_000;
        return (int) result;
    }

    public static void main(String[] args) {
        int n = 12;
        long result = fib(n);
        System.out.println("F(" + n + ") = " + result);
    }
}
