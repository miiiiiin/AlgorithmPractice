package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1260 {

    static int n, m, v;
    static int[][] arr;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new int[m][m];
        visited = new int[m];

        for (int i=0; i<m; i++) {
            System.out.println("i:" + i);

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = arr[y][x] = 1;

        }

        DFS(v);
    }

    public static void DFS(int index) {
        if (index == n) {
            return;
        }

        if (visited[index] == 1) {
            return;
        }

        visited[index] = 1;

        for (int i=1; i<=n; i++) {
            System.out.println("arr check:" + Arrays.toString(arr[index]) + " " + i);
            if (arr[index][i] == 1 && visited[i] == 0) {
                // 방문 횟수 카운트 1씩 증가
//                visited[i] = visited[i] + 1;

                System.out.println("dfs visited:" + i + " " + Arrays.toString(visited));
                DFS(i);
            }
        }
    }
}
