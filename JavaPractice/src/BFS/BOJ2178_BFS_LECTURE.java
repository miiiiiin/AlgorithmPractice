package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_BFS_LECTURE {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    // 방문 배열
    static boolean[][] visited;
    static int [][] A;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int [N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j=0; j<M; j++) {
                System.out.println(line);
                System.out.println(line.substring(j, j+1));
                A[i][j] = Integer.parseInt(line.substring(j, j+1));


            }
        }

        BFS(0, 0);
        // 인덱스가 0부터 시작하니까 마지막 끝 칸은 -1해줘야함
        System.out.println(A[N-1][M-1]);
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        // 시작점 큐에 삽입
        queue.offer(new int[] {i, j});
        // 시작점 방문 표시
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            // 큐에서 데이터 뽑아옴
            int now[] = queue.poll();

            System.out.println("currentqueue:" + queue.toString());

            // 상하좌우 탐 (총 4방향)
            for (int k=0; k<4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                // 인덱스가 배열을 넘어가면 안됨
                if (x >= 0 && y >= 0 && x < N && y < M) {
                    // 방문조건 (0이면 방문 불가, 이미 방문했던 곳 재방문 불가)
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        // depth 값을 갱신해줌
                        A[x][y] = A[now[0]][now[1]] + 1;

                        System.out.println("depth:" + now[0] + " " + now[1]);
                        System.out.println("depth check:" + A[x][y]);

                        // 새로운 x,y를 만들어 큐에 삽입
                        queue.add(new int[] {x, y});


                    }

                }

            }
        }

    }
}
