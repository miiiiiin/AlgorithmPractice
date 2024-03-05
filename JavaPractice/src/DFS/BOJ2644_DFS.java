package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2644_DFS {

    static int n, m, start, end, result = -1;
    static int[][] arr;
    static int[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        visited = new int[n + 1];

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 서로 양방향으로 연결되어 있음을 의미
            arr[x][y] = arr[y][x] = 1;


            System.out.println("arr check:" + Arrays.toString(arr[x]) + " " + Arrays.toString(visited));

        }

        DFS(start, 0);
        System.out.println(result);
    }

    public static void DFS(int index, int cnt) {

        System.out.println("index:" + index);

        if (index == end) {
            result = cnt;
            return;
        }

        System.out.println("arr check:" + Arrays.toString(arr[start]) + " " + Arrays.toString(visited));

        for (int i=1; i<=n; i++) {

            // 방문조건: start와 연결되어 있으면서 아직 방문하지 않은 상태
            if (arr[index][i] == 1 && visited[i] == 0) {
                // 방문 횟수 카운트 1씩 증가
                visited[i] = visited[i] + 1;

                System.out.println("dfs visited:" + i + " " + Arrays.toString(visited));
                DFS(i, cnt+1);
            }
        }
    }
}
