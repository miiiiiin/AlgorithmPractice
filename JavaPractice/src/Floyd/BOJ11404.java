package Floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값
public class BOJ11404 {

    // 최단거리 인접 행렬
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 도시 개수
        int N = Integer.parseInt(br.readLine());
        // 버스 개수
        int M = Integer.parseInt(br.readLine());

        distance = new int[N+1][N+1];

        // 인접행렬 초기화
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                // 시작도시와 도착 도시가 같은 경우는 없으므로 0으로 표시
                if (i == j) distance[i][j] = 0;
                else distance[i][j] = 10000001;
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (distance[a][b] < c) continue;
            distance[a][b] = c;
        }


        // 플로이드 워셜 수행
        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (i == j && distance[i][j] == 0) continue;
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                // i에서 j로 갈 수 없는 경우 (도착할 수 없는 경로)
                if (distance[i][j] == 10000001) {
                    System.out.print("0 ");
                } else {
                    // 도시 i에서 j로 가는데 필요한 최소 비용
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
