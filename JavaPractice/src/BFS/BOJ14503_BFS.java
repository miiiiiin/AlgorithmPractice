package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14503_BFS {
    static int N, M, r, c, d;
    static boolean[][] visited;
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1][M+1];
        arr = new int[N][M];

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(BFS());
    }

    public static int BFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c, d});
        // 청소한 칸의 갯수
        int cleaned = 1;
        // 현재 칸 청소
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            boolean isCleaned = false;
            int x = curr[0];
            int y = curr[1];
            int dir = curr[2];

            for (int i=0; i<4; i++) {
                // 반시계 방향 회전
                dir = (dir + 3) % 4;
                int dx = x + dr[dir];
                int dy = y + dc[dir];
                // 청소기 방향
                // 반시계 방향 회전

                // 방문 조건 : 방문 가능 범위 내에서 다음 이동하는 칸이 아직 방문 및 청소하지 않은 곳
                if (dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy] && arr[dx][dy] == 0) {
                    // 청소
                    visited[dx][dy] = true;
                    cleaned++;
                    queue.add(new int[] {dx, dy, dir});
                    isCleaned = true;
                    // 이동하면 후진하므로 중지
                    break;
                }
            }

            // 4 방향 모두 청소가 불가능한 경우 (이미 청소 완료 or 벽)
            if (!isCleaned) {
                // 후진 (현재 방향의 반대)
                int backDir = (dir + 2) % 4;
                int bx = x + dr[backDir];
                int by = y + dc[backDir];

                // 후진 조건 : 이동 범위 내에 있고, 바라보는 방향의 뒤쪽 칸이 벽이 아닌 경우
                if (bx >= 0 && bx < N && by >= 0 && by < M && arr[bx][by] == 0) {
                    // 방향 그대로 유지한 채로 후진
                    queue.add(new int[] {bx, by, dir});
                }
            }
        }
        return cleaned;
    }
}
