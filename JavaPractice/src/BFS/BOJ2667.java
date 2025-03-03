package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2667 {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Integer> areaCount;
    static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        areaCount = new ArrayList<>();
        number = 1;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                // 방문하지 않았고, 좌표값이 1인 경우 영역 탐색
                if (!visited[i][j] && arr[i][j] == 1) {
                    int size = BFS(i, j);
                    areaCount.add(size);
                    number++;
                }
            }
        }

        Collections.sort(areaCount);

        System.out.println(areaCount.size());
        for (int i=0; i<areaCount.size(); i++) {
            System.out.println(areaCount.get(i));
        }
    }

    public static int BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> coords = new ArrayList<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];
            count++;
            coords.add(curr);

            // 상하좌우 탐색
            for (int i=0; i<4; i++) {
                // 상하좌우로 이동한 row, col
                // 연결 조건 : 좌, 우, 위, 아래 방향으로 연결된 경우 같은 단지로 간주
                int dx = cx + dr[i];
                int dy = cy + dc[i];
                // 방문 조건 (범위 벗어나거나 재방문 아닌 경우)
                if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                    // 이미 방문한 곳이 아니고, 좌표가 1로 채워져 있을 경우
                    if (!visited[dx][dy] && arr[dx][dy] == 1) {
                        queue.add(new int[] {dx, dy});
                        visited[dx][dy] = true;
                    }
                }
            }
        }

        for (int[] coord : coords) {
            arr[coord[0]][coord[1]] = number;
        }
        return count;
    }
}
