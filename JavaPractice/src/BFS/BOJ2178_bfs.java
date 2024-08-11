package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//import java.util.*;


public class BOJ2178_bfs {
    static int[][] arr;
    static boolean[][] visited;
    public static int bfs(int row, int col) {
        int n = arr.length;
        int m = arr[0].length;

        // 시작점 방문표시
        visited[row][col] = true;

        // 상하좌우
        int [][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        // Queue 선언
        Queue<int []> queue = new ArrayDeque<>();
        // 시작 지점 (x, y) 큐에 삽입
        queue.offer(new int[] {row, col, arr[row][col]});

        // 큐 빌때까지 실행
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // row
            int x = current[0];
            // col
            int y = current[1];
            int count = current[2];

            if (x == n-1 && y == m-1) {
                // 최단 경로 반환
                return count;
            }

//            System.out.println("queuecheck:" + x + " " + y);

            // 상하좌우 이동
            for (int[] dir: dirs) {
                // 상하좌우로 이동한 row, col의 좌표
                int dx = x + dir[0];
                int dy = y + dir[1];

                // 방문 조건
                // 인덱스가 배열을 넘어가면 안됨
                if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
                    // 방문 가능 조건: 칸의 숫자가 0이 아니어야 하고 아직 방문하지 않은 상태
                    if (arr[dx][dy] != 0 && !visited[dx][dy]) {

                        System.out.println("dirs:" + dir[0] + " " + dir[1]);
//                        System.out.println("dxdy:" + dx + " " + dy);
//                        System.out.println("dirscheck:" + arr[dx][dy] + " " + !visited[dx][dy]);

                        visited[dx][dy] = true;
                        // depth 값을 갱신해줌
                        arr[dx][dy] = arr[current[0]][current[1]] + 1;
                        count = arr[dx][dy];

//                        System.out.println("depth:" + current[0] + " " + current[1]);
                        System.out.println("depth check:" + arr[dx][dy]);

                        // 새로운 x,y를 만들어 큐에 삽입
                        queue.add(new int[] {dx, dy, count});

                    }
                }
            }
        }
        // 도착하지 못한 경우
        return  0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int [n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

//        bfs(0, 0);
//        // 인덱스가 0부터 시작하니 마지막 칸의 좌표값에 -1을 해줘야함.
//        System.out.println(arr[n-1][m-1]);

        System.out.println(bfs(0, 0));

        for (int a[]: arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
