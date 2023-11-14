package assignment2;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MatrixChainPractice3 {
    public static void main(String[] args) {
        int[][] dimensions = readMatrixDimensionsFromFile("./src/assignment2/input1.txt");
        int n = dimensions.length;

//        // m[i][j] will store the minimum number of scalar multiplications needed to compute the product A[i] * A[i+1] * ... * A[j]
//        int[][] m = new int[n][n];
//
//        // s[i][j] stores the index of the matrix split that achieved the optimal solution
//        int[][] s = new int[n][n];
//
//        // Perform matrix chain multiplication and fill in m and s tables
//        matrixChainOrder(dimensions, m, s);
//
//        // Print the minimum scalar multiplications and optimal order
//        int minScalarMultiplications = m[1][n - 1];
//        System.out.println("Minimum Scalar Multiplications: " + minScalarMultiplications);
//
//        System.out.println("Optimal Matrix Chain Order:");
//        printOptimalOrder(s, 1, n - 1);
    }

    public static int[][] readMatrixDimensionsFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int[][] dimensions = null;
            int currentIndex = -1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                int matrixCount = 0;
//
                if (line.startsWith("matrix")) {
//                    int matrixCount = Integer.parseInt(line.split(" ")[2]);
//                    dimensions = new int[matrixCount][2];
//                    currentIndex = 0;
                        String[] parts = line.split(" ");
//                        dimensions[matrixCount] = Integer.parseInt(parts[parts.length - 2]);


                    System.out.println("parts:" + Arrays.toString(parts));
                    System.out.println("demensions check:" + dimensions + " " + matrixCount);
                }

//                else if (dimensions != null && currentIndex >= 0 && currentIndex < dimensions.length) {
//                    String[] parts = line.split(" ");
//                    if (parts.length == 2) {
//                        // Remove any colons from the input
//                        String rowsStr = parts[0].replace(":", "");
//                        String colsStr = parts[1].replace(":", "");
//                        int rows = Integer.parseInt(rowsStr);
//                        int cols = Integer.parseInt(colsStr);
//                        dimensions[currentIndex] = new int[]{rows, cols};
//                        currentIndex++;
//                    }
//                }
            }
            return dimensions;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void matrixChainOrder(int[][] p, int[][] m, int[][] s) {
        int n = p.length - 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int cost = m[i][k] + m[k + 1][j] + p[i - 1][0] * p[k][1] * p[j][1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static void printOptimalOrder(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("Matrix " + i);
        } else {
            System.out.print("(");
            printOptimalOrder(s, i, s[i][j]);
            printOptimalOrder(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
