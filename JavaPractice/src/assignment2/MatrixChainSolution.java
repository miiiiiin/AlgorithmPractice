package assignment2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixChainSolution {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/assignment2/input1.txt"));
            String line;
            int matrixCount = 0;
            int[] matrixSizes = new int[7];

            while ((line = br.readLine()) != null) {
                if (line.startsWith("matrix")) {
                    matrixCount++;
                    String[] parts = line.split(" ");
                    matrixSizes[matrixCount] = Integer.parseInt(parts[parts.length - 2]);
                }
            }

            System.out.println("행렬 카운트:" + matrixCount);
            br.close();

            // 동적 프로그래밍 알고리즘을 사용하여 최적 순서 및 스칼라 곱 계산
            int n = matrixCount;
            int[][] m = new int[n + 1][n + 1];
            int[][] s = new int[n + 1][n + 1];

            for (int d = 2; d < n; d++) {
                for (int i = 1; i < n - d + 1; i++) {
                    int j = i + d - 1;
                    m[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        int cost = matrixSizes[i - 1] * matrixSizes[k] * matrixSizes[j] + m[i][k] + m[k + 1][j];
                        if (cost < m[i][j]) {
                            m[i][j] = cost;
                            s[i][j] = k;
                        }
                    }
                }
            }

            // 최적 순서 및 스칼라 곱 수 계산
            int minMultiplications = m[1][n - 1];
            int scalarMultiplications = calculateScalarMultiplications(matrixSizes, s, 1, n - 1);
            String optimalSequence = getOptimalSequence(s, 1, n - 1);

            // 결과 출력
            System.out.println("행렬 곱셈의 결과: " + minMultiplications);
            System.out.println("최적 시퀀스에서 행렬이 곱해질 때의 스칼라 곱의 수: " + scalarMultiplications);
            System.out.println("행렬 곱셈의 최적 순서: " + optimalSequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 최적 순서 출력 함수
    private static String getOptimalSequence(int[][] s, int i, int j) {
        if (i == j) {
            return "A" + i;
        } else {
            String left = getOptimalSequence(s, i, s[i][j]);
            String right = getOptimalSequence(s, s[i][j] + 1, j);
            return "(" + left + " x " + right + ")";

        }
    }

    // 스칼라 곱 계산
    public static int calculateScalarMultiplications(int[] dimensions, int[][] s, int i, int j) {
        if (i == j) {
            return 0;
        }
        int k = s[i][j];
        return calculateScalarMultiplications(dimensions, s, i, k) + calculateScalarMultiplications(dimensions, s, k + 1, j)
                + dimensions[i - 1] * dimensions[k] * dimensions[j];
    }
}

