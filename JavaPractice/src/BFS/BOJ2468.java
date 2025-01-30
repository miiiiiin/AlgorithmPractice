package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2468 {
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> areaCount;
    static int N;
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};

    // 높이는 1이상 100 이하의 정수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        areaCount = new ArrayList<>();

        // 안전 영역 최대 개수 초기화
        int maxSafeArea = 0;

        // 가장 높은 지점 값 초기화
        int maxHeight = 0;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 물에 잠기는 최고 높이 갱신
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        // 높이 0부터 maxHeight까지 모든 경우 탐색
        for (int h=0; h <= maxHeight; h++) {
            // 방문 배열 초기화
            visited = new boolean[N][N];
            int count = 0;

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    // 방문한 적 없고, 침수되지 않은 지역이면 탐색
                    if (!visited[i][j] && map[i][j] > h) {
                        BFS(i, j, h);
                        count++;
                    }
                }
            }
            // 안전 영역 개수 비교하여 갱신
            maxSafeArea = Math.max(count, maxSafeArea);
        }

        System.out.println(maxSafeArea);
    }

    public static void DFS(int x, int y) {
        visited[x][y] = true;

    }
    public static void BFS(int x, int y, int h) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int dx = r + dr[i];
                int dy = c + dc[i];

                // 방문 조건 탐색
                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy] && map[dx][dy] > h) {
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx, dy});
                }
            }
        }
    }
}
