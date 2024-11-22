package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class PGM1844 {
    public static void main(String[] args) {
        int[][] maps = new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
//        int[][] maps = new int[][] {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
        int answer = solution(maps);
        System.out.println(answer);
    }

    public static int solution(int[][] maps) {
        int answer = 0;
        boolean[][] visited = new boolean[maps.length][maps[0].length];

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        BFS(0, 0, maps, visited, dr, dc);
        int result = maps[maps.length-1][maps[0].length-1];

        if (result == 1) answer = -1;
        else answer = result;
        return answer;
    }

    public static void BFS(int i, int j, int[][] maps, boolean[][] visited, int[] dr, int[] dc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int x = curr[0];
            int y = curr[1];

            // 동서남북 탐색
            for (int k=0; k<4; k++) {
                // 동서남북 이동한 좌표값
                int dx = x + dr[k];
                int dy = y + dc[k];

                // 배열 범위 나가지 않도록
                if (dx >= 0 && dx < maps.length && dy >= 0 && dy < maps[0].length) {
                    // 방문 조건: 전에 방문하지 않았고, 칸의 수가 1일 때
                    if (!visited[dx][dy] && maps[dx][dy] == 1) {
                        visited[dx][dy] = true;
                        // 현재 칸에서 1씩 증가시켜 다음 이동 칸의 값 갱신 (이동 칸 수 카운트)
                        maps[dx][dy] = maps[x][y] + 1;
                        queue.offer(new int[] {dx, dy});
                    }
                }

            }
        }
    }
}
