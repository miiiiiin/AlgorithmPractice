package assignment2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


//연쇄 행렬 곱셈은 여러 행렬을 연속적으로 곱하는 문제입니다. 예를 들어, 행렬 A, B, C가 있을 때, (AB)C와 A(BC)를 곱하는 순서에 따라 곱셈 연산의 횟수와 비용이 달라집니다. 목표는 이러한 연쇄 곱셈을 수행할 때 가장 적은 연산 횟수로 결과를 얻는 것입니다.
//
//        예를 들어, 행렬 A가 10x30, B가 30x5, C가 5x60 크기라고 가정하겠습니다. 연산 순서에 따라 비용이 달라집니다.
//
//        (AB)C를 곱하는 경우:
//
//        (AB)의 크기: 10x5
//        (AB)C의 크기: 10x60
//        곱셈 연산 횟수: 10 * 30 * 5 + 10 * 5 * 60 = 1500 + 3000 = 4500
//        A(BC)를 곱하는 경우:
//
//        (BC)의 크기: 30x60
//        A(BC)의 크기: 10x60
//        곱셈 연산 횟수: 30 * 5 * 60 + 10 * 30 * 60 = 9000 + 18000 = 27000
//        이를 통해 (AB)C를 곱하는 것이 더 효율적임을 알 수 있습니다. 동적 계획법을 사용하여 이러한 결정을 자동으로 내릴 수 있습니다.

public class MatrixPractice {

    // 행렬 곱셈의 순서를 선택하여 최소 연산 횟수를 반환

    public static int matrixChainOrder(int[] p) {
//        n은 행렬의 개수보다 1 적은 값
        int n = p.length - 1;
//        m 배열은 크기가 (n, n)인 2차원 배열로, 최소 연산 횟수를 저장
        int[][] m = new int[n][n];

//        중첩된 두 개의 반복문을 사용하여 모든 가능한 구간의 최소 연산 횟수를 계산합니다.
//        바깥쪽 반복문(len)은 구간의 길이를 나타내며 2부터 시작

        System.out.println(("n size:" + n));
        for (int len = 2; len <= n; len++) {
            System.out.println(("n-len size:" + (n - len)));
            for (int i = 0; i < n - len + 1; i++) {
//                구간의 끝점(j)은 시작점(i)과 길이(len)를 통해 계산

                int j = i + len - 1;

                System.out.println(("j size:" + j + " " + i + " " + (len - 1)));
//                초기에 m 배열의 모든 값을 무한대(Integer.MAX_VALUE)로 초기화
                m[i][j] = Integer.MAX_VALUE;
//                각 구간(i에서 j)에 대해 가능한 모든 중간 지점(k)을 반복하여 최소 연산 횟수를 계산합니다.
//                cost 변수에 중간 지점(k)을 사용한 경우의 연산 비용을 계산하고, 이 값이 현재 구간(i에서 j)의 최소 연산 비용보다 작으면 갱신

                for (int k = i; k < j; k++) {
                    System.out.println("k size:" + k + " " + j);
                    int cost = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];

                    System.out.println("cost value:" + cost + " " + m[i][j]);
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                    }
                }
            }
        }
//        m[0][n - 1]에 저장된 값은 모든 행렬을 최적 순서로 곱할 때의 최소 연산 횟수
//        이 값을 반환하여 최소 연산 횟수를 얻는다
        System.out.println("return:" + m[0][n-1]);
        return m[0][n - 1];
    }

//    main 함수에서는 행렬의 크기 배열 p를 정의하고 matrixChainOrder 함수를 호출하여 최소 연산 횟수를 계산
    public static void main(String[] args) {
        int[] dimensions = {10, 30, 5, 60};
        int minOperations = matrixChainOrder(dimensions);
        System.out.println("Minimum number of operations: " + minOperations);
//        주어진 행렬 크기 배열 p를 기반으로 최적의 순서를 찾고, 이로부터 최소 연산 횟수를 계산
    }
}
