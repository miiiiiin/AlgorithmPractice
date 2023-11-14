package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MaxtrixChain {
    public static void main(String[] args) {
        try {
            // 파일에서 행렬 정보 읽기
            BufferedReader br = new BufferedReader(new FileReader("./src/assignment2/input1.txt"));
            String line;
            Matrix[] matrices = new Matrix[10]; // 최대 10개의 행렬
            int matrixCount = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("matrix")) {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    tokenizer.nextToken(); // "matrix"
                    int rows = Integer.parseInt(tokenizer.nextToken());
                    int cols = Integer.parseInt(tokenizer.nextToken());

                    int[][] matrixData = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        line = br.readLine();
                        StringTokenizer matrixTokenizer = new StringTokenizer(line);
                        for (int j = 0; j < cols; j++) {
                            matrixData[i][j] = Integer.parseInt(matrixTokenizer.nextToken());
                        }
                    }

                    matrices[matrixCount] = new Matrix(rows, cols, matrixData);
                    matrixCount++;
                }
            }

            // 최소 곱셈 수 및 최적 순서 계산
            int[] sequence = matrixChainOrder(matrices, matrixCount);
            int minMultiplications = sequence[0];
            int[] optimalOrder = new int[matrixCount - 1];
            System.arraycopy(sequence, 1, optimalOrder, 0, matrixCount - 1);

            // 결과 출력
            System.out.println("Minimum Multiplications: " + minMultiplications);
            System.out.print("Optimal Sequence: ");
            for (int i : optimalOrder) {
                System.out.print("Matrix " + (i + 1) + " -> ");
            }
            System.out.println("Matrix " + matrixCount);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] matrixChainOrder(Matrix[] matrices, int n) {
        int[] minOperations = new int[n];
        int[] splitPoints = new int[n - 1];

        for (int i = 1; i < n; i++) {
            minOperations[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                int cost = minOperations[j] + minOperations[i - j - 1] + matrices[j].rows * matrices[i].rows * matrices[i].cols;
                if (cost < minOperations[i]) {
                    minOperations[i] = cost;
                    splitPoints[i - 1] = j;
                }
            }
        }

        int[] result = new int[n];
        result[0] = minOperations[n - 1];
        System.arraycopy(splitPoints, 0, result, 1, n - 1);
        return result;
    }
}

class Matrix {
    int rows;
    int cols;
    int[][] data;

    public Matrix(int rows, int cols, int[][] data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
    }
}
