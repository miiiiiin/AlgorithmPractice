package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14503_CLASS {
    static int N, M, r, c, d;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
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
        System.out.println(BFS());
    }

    public static int BFS() {
        int cleaned = 1;
        Queue<Cleaner> queue = new LinkedList<>();
        queue.offer(new Cleaner(r, c, d));

        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Cleaner curr = queue.poll();
            int x = curr.r;
            int y = curr.c;
            int dir = curr.d;

            boolean isCleaned = false;

            // 동서남북 방향 이동
            for (int i=0; i<4; i++) {
                // 반시계 방향 이동
                dir = (dir + 3) % 4;
                // 반시계 방향으로 회전한 좌표값
                int dx = x + dr[dir];
                int dy = y + dc[dir];

                // 방문 조건 : 범위 내 && 방문하지 않은 곳 && 아직 청소되지 않은 곳
                if (dx >= 0 && dx < N && dy >=0 && dy < M && !visited[dx][dy] && map[dx][dy] == 0) {
                    visited[dx][dy] = true;
                    cleaned++;
                    queue.add(new Cleaner(dx, dy, dir));
                    isCleaned = true;
                    break;
                }
            }

            // 동서남북 방향 다 청소하지 못하는 경우 (이미 청소 완료 & 벽)
            if (!isCleaned) {
                // 현재 방향에서 반대 방향
                int backDir = (dir + 2) % 4;
                // 현재 방향에서 반대 방향으로 이동한 좌표값
                int bx = x + dr[backDir];
                int by = y + dc[backDir];

                // 후진 조건 : (바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진)
                //  바라보는 방향의 뒤쪽 칸이 벽이 아닌 경우
                if (bx >= 0 && bx < N && by >= 0 && by < M && map[bx][by] == 0) {
                    // 바라보는 방향 유지
                    queue.add(new Cleaner(bx, by, dir));
                }
            }
        }

        return cleaned;
    }
}

class Cleaner {
    int r, c, d;

    public Cleaner(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}