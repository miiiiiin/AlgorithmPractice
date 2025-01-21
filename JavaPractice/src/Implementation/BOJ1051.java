package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1051 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 현재 시점 최대 정사각형의 크기 : 1x1
        int maxSize = 1;

        // 정사각형의 최대 한 변의 길이
        int maxLength = Math.min(N, M);

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        // 한 변의 길이가 k + 1인 정사각형을 탐색 (큰 것부터 역순으로 탐색)
        for (int k=maxLength-1; k>=0; k--) {
            // 아래쪽 꼭짓점의 세로 좌표는 i + k이고, 이 값은 N - 1을 넘지 않아야 함 (정사각형이 배열을 벗어나지 않도록)
            for (int i=0; i < N - k; i++) {
                // 오른쪽 꼭짓점의 가로 좌표는 j + k이고, 이 값은 M - 1을 넘지 않아야 함
                for (int j=0; j < M - k; j++) {
                    // 꼭짓점 좌측 상단
                    // map[i][j]
                    // 꼭짓점 우측 상단
                    // map[i][j+k]
                    // 꼭짓점 좌측 하단
                    // map[i+k][j]
                    // 꼭짓점 우측 하단
                    // map[i+k][j+k]
                    if (map[i][j] == map[i][j+k] && map[i][j] == map[i+k][j] && map[i][j] == map[i+k][j+k]) {
                        // 가장 큰 정사각형
                        maxSize = (k + 1) * (k + 1);
                        System.out.println(maxSize);
                        return;
                    }
                }
            }
        }
        System.out.println(maxSize);
    }
}
