package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14503 {

    static int N, M, r, c, d;
    static boolean[][] visited;
    static int[][] arr;

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
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
//                System.out.println("arr:" + i + " " + j + " " + num + " " + arr[i][j]);
//                System.out.println(Arrays.toString(arr[i]));
            }
        }
        System.out.println(BFS());
    }

    public static int BFS() {
        int dirsCount = 0;
        int answer = 0; // 청소 칸 개수

        visited[r][c] = true;

        // 0: 북, 1: 동, 2: 남, 3: 서
        // 상하좌우
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int []> queue = new ArrayDeque<>();

        queue.offer(new int[] {r, c, d});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            System.out.println("curr:" + Arrays.toString(curr));

            // row
            int x = curr[0];
            // column
            int y = curr[1];
            // 방향
            int d = curr[2];


            while (dirsCount < 4) {
                // 반시계 방향으로 회전하는 방향
                int left = (d + 3) % 4;
                System.out.println("left:" + d + " " + left);

                int nx = x + dx[left];
                int ny = y + dy[left];

                System.out.println("newdir:" + arr[nx][ny] + " " + nx + " " + ny);

                // 방문 조건 : 왼쪽으로 빈 공간이 있고, 아직 청소하지 않은 곳
                if (arr[nx][ny] == 0 && !visited[nx][ny]) {
                    // 회전 후 전진
                    queue.offer(new int[] {nx, ny, left});
                    visited[nx][ny] = true; // 청소
                    System.out.println("visited:" + Arrays.toString(visited));
                    answer++; // 청소 칸 수 증가
                    break;
                } else {
                    // 왼쪽 방향에 빈 공간이 없다면 반시계 방향으로 회전
                    d = left;
                }
                dirsCount++;
            }

            // 주변 4칸에 청소 가능한 칸이 없을 경우
            // 바라보는 방향 유지한 채로 한 칸 후진해야 한다
            if (dirsCount == 4) {
                int back = (d+2) % 4;
                if (arr[r + dx[back]][c + dy[back]] == 1) {
                    // 뒤 쪽이 벽이면 작동 종료
                    break;
                } else {
                    // 후진
                    queue.offer(new int[] {r + dx[back], c + dy[back], d});
                }
            }
        }
        return answer;
    }
}
