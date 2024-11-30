package Floyd;

import java.util.Scanner;

public class BOJ11403 {

    // 그래프의 인접 행렬
    static int[][] maps;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정점의 개수
        int N = sc.nextInt();
        maps = new int[N+1][N+1];

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (i == j) maps[i][j] = 0;
//                else maps[i][j] = 10000001;
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                int value = sc.nextInt();
                maps[i][j] = value;
            }
        }


        // 플로이드 워셜 수행 (변형)
        // 정점 i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력
        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    // k 거치는 모든 경로 중 하나라도 연결된 경로가 있으면 i, j는 연결 노드로 취급
                    if (maps[i][k] == 1 && maps[k][j] == 1) {
                        maps[i][j] = 1;
                    }
                }
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
    }
}
