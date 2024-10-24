package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

    static int N, M;
    static int[][] arr;
    static boolean[][] visited;

    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        // 시작점 인덱스에서 bfs 시작
        BFS(0, 0);
        System.out.println(arr[N-1][M-1]);
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});

        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int k=0; k<4; k++) {
                int x = curr[0] + dirs[k][0];
                int y = curr[1] + dirs[k][1];

                // 배열 인덱스 넘어가는 것 방지
                if (x >= 0 && y >= 0 && x < N && y < M) {
                    // 방문 조건
                    if (arr[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        // 한 칸 이동 후, 현재 좌표에서 1씩 증가하여 이동 횟수 계산
                        arr[x][y] = arr[curr[0]][curr[1]] + 1;
                        // 큐에 새로운 데이터 삽입
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
