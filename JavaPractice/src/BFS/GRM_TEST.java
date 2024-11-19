package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GRM_TEST {

    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int N;
    static List<Integer> areaCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        areaCount = new ArrayList<>();

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                // 아직 방문하지 않았고, 좌표값이 1인 경우 영역 탐색 시작
                if (map[i][j] == 1 && !visited[i][j]) {
                    // 1로만 채워진 영역 하나에 들어있는 칸 개수
                    int size = DFS(i, j);
                    areaCount.add(size);
                }
            }
        }

        System.out.println(areaCount.size());
        for (int count : areaCount) {
            System.out.print(count + " ");
        }
    }

    static public int BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        int size = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int cx = curr[0];
            int cy = curr[1];
            size++;

            // 상하좌우 탐색
            for (int i=0; i<4; i++) {
                // 상하좌우로 이동한 row, col
                int dx = cx + dr[i];
                int dy = cy + dc[i];
                // 방문 조건 (범위 지정 및 재방문 아닐 경우)
                if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                    // 이미 방문한 곳이 아니고, 좌표가 1로 채워져 있을 때
                    if (!visited[dx][dy] && map[dx][dy] == 1) {
                        queue.add(new int[] {dx, dy});
                        visited[dx][dy] = true;
                    }
                }
            }
        }
        return size;
    }

    static public int DFS(int x, int y) {
        visited[x][y] = true;
        // 현재 칸 포함
        int size = 1;

        // 상하좌우 탐색
        for (int i=0; i<4; i++) {
            int dx = x + dr[i];
            int dy = y + dc[i];

            if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                if (!visited[dx][dy] && map[dx][dy] == 1) {
                    // 연결된 칸 탐색
                    size += DFS(dx, dy);
                }
            }
        }
        return size;
    }
}
