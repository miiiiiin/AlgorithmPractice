package assignment3;

import java.util.Arrays;
import java.util.Scanner;

public class OptimalPath {

    public static int[] getOptimalPath(int n, int[][] net) {
        // 최적의 경로 저장할 배열
        // dp[i][j]는 Router[i][j]까지 도달하는데 걸리는 최소 시간을 저장하는 DP 배열
        int[][] dp = new int[n][5];
        // 최적의 경로를 추적하기 위한 배열(현재 라우터에서 이전 행에서 선택한 라우터의 인덱스를 저장하는 배열)
        int[][] path = new int[n][5];

        for (int i = 0; i < n; i++) {
//            dp 배열의 모든 원소를 초기값 Integer.MAX_VALUE로 채웁니다. 이는 나중에 최소값을 찾을 때 초기값과 비교하기 위한 것입니다. 이 초기값은 나중에 최소값을 찾는 과정에서 업데이트될 것
            Arrays.fill(dp[i], Integer.MAX_VALUE);
//            System.out.println(Arrays.toString(dp[i]));
        }

        // 첫 번째 행의 초기값을 설정
//        net 배열의 첫 번째 행의 값을 dp 배열의 첫 번째 행으로 복사(네트워크의 첫 번째 라우터에서 메시지를 전송하는데 걸리는 시간을 나타냄)
//        System.arraycopy(net[0], 0, dp[0], 0, 5);
        for (int j = 0; j < 5; j++) {
            dp[0][j] = net[0][j];
        }

//        각 행마다 이전 행의 최소 시간을 활용하여 현재 행의 최소 시간을 갱신합니다.
//        경로를 추적하기 위해 path 배열도 함께 갱신합니다.

//        printDP(dp);

//        첫 번째 행부터 마지막 행까지 반복합니다. 각 행은 이전 행에서 최소 시간을 계산하며 현재 행에 도달하는 최적의 경로를 찾아갑니다
        for (int i = 1; i < n; i++) {
//            각 행에서 열을 순회하며 현재 라우터에 도달하는 최소 시간을 계산
            for (int j = 0; j < 5; j++) {
//                현재 라우터에 도달하는 최소 시간을 찾기 위한 변수 minPrev를 초기화합니다. 처음에는 최대값으로 설정하여 미래의 시간을 비교할 때 영향을 주지 않도록 합니다.
                int minPrev = Integer.MAX_VALUE;

//                현재 라우터에서 이전 행에서 선택한 라우터와 연결할 수 있는 모든 경우에 대한 루프
                for (int k = -1; k <= 1; k++) {
//                    j + k가 현재 열에서 이전 행의 세 라우터 중 하나를 나타냄

                    if (j + k >= 0 && j + k < 5) {
//                        이전 행에서 선택한 라우터와 연결할 때의 시간이 현재까지의 최소 시간보다 작다면 업데이트를 수행
                        if (dp[i - 1][j + k] < minPrev) {
//                            minPrev를 갱신하고, 이때의 라우터 인덱스를 path[i][j]에 저장합니다.
//                            path 배열은 최적의 경로를 역추적하기 위해 사용
                            minPrev = dp[i - 1][j + k];
                            path[i][j] = j + k;

//                            printDP(path);
                        }
                    }
                }
//                현재 라우터에 도달하는 최소 시간은 net[i][j]와 이전 행에서 선택한 라우터와 연결할 때의 최소 시간인 minPrev를 더한 값
                dp[i][j] = net[i][j] + minPrev;
            }
        }

        // 최적 경로 찾기
//        마지막 행에서 최소 시간을 가지는 열을 찾아 최적 경로의 끝점을 결정합니다.
//                각 행에서 선택한 열의 인덱스를 역추적하여 최적의 경로를 찾습니다.

//        최소 시간을 저장하는 변수 minTime을 최대값으로 초기화합니다. 이 변수는 현재까지 찾은 최소 시간을 나타냄
        int minTime = Integer.MAX_VALUE;
//        최소 시간을 가지는 라우터의 인덱스를 저장하는 변수 minIndex를 초기화합니다. 초기값은 -1로 설정
        int minIndex = -1;
//        마지막 행의 각 열을 순회하면서 최소 시간을 찾음
        for (int j = 0; j < 5; j++) {
            System.out.println(Arrays.toString(dp[n-1]) + " " + minTime);
//            현재 열의 라우터에 도달하는 최소 시간이 현재까지 찾은 최소 시간보다 작으면 업데이트를 수행
            if (dp[n - 1][j] < minTime) {
//                현재 열의 라우터에 도달하는데 걸리는 최소 시간(현재까지 찾은 최소 시간)으로 업데이트
                minTime = dp[n - 1][j];
//                최소 시간을 가지는 라우터의 인덱스를 현재 열 j로 업데이트
                minIndex = j;
            }
        }
        // 마지막 행에서 최소 시간을 가지는 라우터를 찾아 minTime과 해당 라우터의 인덱스 minIndex를 업데이트합니다. 따라서 이후에 minIndex를 이용하여 최적의 경로를 역추적할 수 있습니다.


        // 최적 경로 역추적
        int[] optimalPath = new int[n]; // 배열의 크기는 행의 개수 n과 동일하게 설정
//        optimalPath[n - 1] = minIndex; // 마지막 행에서 최소 시간을 가지는 라우터의 인덱스(minIndex를 optimalPath 배열의 마지막 인덱스에 저장합니다. 이것이 최적의 경로의 시작점)

//        마지막 행부터 시작하여 첫 번째 행까지 거꾸로 이동하는 루프
        for (int i = n - 1; i > 0; i--) {
//            현재 행에서 최소 시간을 가지는 라우터의 인덱스를 path 배열을 통해 가져옴. 이것은 이전 행에서 선택한 라우터의 인덱스
            minIndex = path[i][minIndex];
            System.out.println(minIndex);
            optimalPath[i - 1] = minIndex;
            // 현재 행에서 최적의 경로를 찾아내어 optimalPath 배열에 저장. 현재 행의 최적 경로는 이전 행에서 선택한 라우터의 인덱스(minIndex)
            System.out.println(Arrays.toString(optimalPath));
        }

        return optimalPath;
        // 이렇게 하면 최적의 경로가 optimalPath 배열에 저장되게 됩니다. 이 배열을 출력하거나 활용하여 최적의 경로를 사용할 수 있습니다. 이 부분은 동적 계획법의 결과를 해석하여 사용하는 단계로, 경로를 구성하는 각 라우터의 인덱스를 차례로 optimalPath 배열에 저장하면서 역추적
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[][] net = {
//                { 0,   0,   0,  1000, 400},
//                {500, 500, 500, 1000,  1 },
//                { 0,   0,   0,  1000,  1 },
//                { 0,   0,   0,  1000,  1 },
//                { 0,   0,   0,  1000,  1 },
//        };
//
//        int[] optimalPath = getOptimalPath(n, net);
//        System.out.println(Arrays.toString(optimalPath));

        Scanner scanner = new Scanner(System.in);

        System.out.print("n을 입력하세요: ");
        int n = scanner.nextInt();

        int[][] net = new int[n][5];
        System.out.println("라우터 네트워크의 각 라우터에 대한 시간을 입력하세요:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                net[i][j] = scanner.nextInt();
            }
        }

        int[] optimalPath = getOptimalPath(n, net);

        System.out.println(Arrays.toString(optimalPath));
    }
    public static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}

//        dp[i][j]는 net[i][j] 값과 이전 행의 dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1] 중 최소값을 더하여 갱신됩니다.
//        각 행마다 최소값을 가지는 열의 인덱스를 기록해둡니다.
//        마지막 행에서 최소값을 가지는 열부터 시작하여 각 행에서 선택한 열의 인덱스를 역추적하여 최적의 경로를 구합니다.


//    dp 배열은 최소 시간을 저장하는데 사용됩니다.
//        path 배열은 최적의 경로를 추적하기 위해 사용됩니다.
//        이중 반복문을 사용하여 각 행과 열에 대해 최소 시간을 계산하고, 경로를 업데이트합니다.
//        마지막 행에서 최소 시간을 가지는 열을 찾고, 이를 기반으로 최적의 경로를 추적합니다.
//        최적 경로를 나타내는 배열을 반환합니다.