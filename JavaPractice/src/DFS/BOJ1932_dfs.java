package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

// 정수 삼각형
public class BOJ1932_dfs {
    static int n;
    static int [][] map;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][n];

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                if (st.hasMoreTokens())
                    map[i][j] = Integer.parseInt(st.nextToken());
                else
                    map[i][j] = -1;
            }
        }

        dfs(0, 0, 0, 1);
        System.out.println(ans);
    }

    private static void dfs(int cur_x, int cur_y, int sum, int cnt) {
        sum += map[cur_x][cur_y];

        if (cnt==n) {
            ans = Math.max(ans, sum);
            return;
        }

        cur_x += 1;

        if (cur_x >= 0 && cur_y < n) {
            if (map[cur_x][cur_y] != -1)
                dfs(cur_x, cur_y, sum, cnt+1);

            if (cur_y+1 >= 0 && cur_y+1 <n)
                if (map[cur_x][cur_y+1] != -1)
                    dfs(cur_x, cur_y+1, sum, cnt+1);
        }
    }
}
