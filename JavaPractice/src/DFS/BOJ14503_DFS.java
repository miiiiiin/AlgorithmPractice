package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Cleaner;
import java.util.StringTokenizer;

public class BOJ14503_DFS {
    static int N, M, r, c, d;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int cleaned = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cleaned = 0;
        DFS(r, c, d);
        System.out.println(cleaned);
    }

    public static void DFS(int x, int y, int dir) {
        if (!visited[x][y]) {
            visited[x][y] = true;
            cleaned++;
        }

        for (int i = 0; i < 4; i++) {
            // 반시계 방향으로 회전
            dir = (dir + 3) % 4;
            int dx = x + dr[dir];
            int dy = y + dc[dir];

            // 조건: 범위 내 && 방문하지 않은 곳 && 벽이 아닌 곳
            if (dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy] && map[dx][dy] == 0) {
                DFS(dx, dy, dir);
                // 이동했으면 더 이상 탐색하지 않음
                return;
            }
        }

        int backDir = (dir + 2) % 4;
        int bx = x + dr[backDir];
        int by = y + dc[backDir];

        // 후진 조건: 벽이 아닌 경우 후진
        if (bx >= 0 && bx < N && by >= 0 && by < M && map[bx][by] == 0) {
            DFS(bx, by, dir); // 후진하면서 방향 유지
        }
    }
}
