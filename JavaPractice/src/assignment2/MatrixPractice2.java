package assignment2;
import java.util.Arrays;

public class MatrixPractice2 {

    public static int matrixChainOrder(int[] p) {
        int n = p.length - 1; // 행렬의 개수
        int[][] m = new int[n][n]; // 최소 연산 횟수 저장 배열

        for (int i = 0; i < n; i++) {
            Arrays.fill(m[i], 0); // 초기값은 0으로 설정
        }

        for (int len = 2; len <= n; len++) { // 구간 길이에 따라
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1; // 구간의 끝점
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                    }
                }
            }
        }

        return m[0][n - 1];
    }

    public static int calculateScalarMultiplications(int[] p) {
        int n = p.length - 1; // 행렬의 개수
        int[][] m = new int[n][n]; // 최소 연산 횟수 저장 배열
        int[][] s = new int[n][n]; // 최적 순서 저장 배열

        for (int i = 0; i < n; i++) {
            Arrays.fill(m[i], 0); // 초기값은 0으로 설정
            Arrays.fill(s[i], 0); // 초기값은 0으로 설정
        }

        for (int len = 2; len <= n; len++) { // 구간 길이에 따라
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1; // 구간의 끝점
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k; // 최적 순서 저장
                    }
                }
            }
        }

        return m[0][n - 1];
    }

    public static void main(String[] args) {
        int[] p = {10, 30, 5, 60};
        int minOperations = matrixChainOrder(p);
        int scalarMultiplications = calculateScalarMultiplications(p);

        System.out.println("Minimum operations: " + minOperations);
        System.out.println("Scalar multiplications: " + scalarMultiplications);
    }
}
