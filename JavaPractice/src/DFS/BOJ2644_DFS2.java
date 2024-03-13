package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 2차원 배열이 아닌 그래프 형태로의 풀이
public class BOJ2644_DFS2 {

    static int n, start, end, result = -1;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 양방향 위해서
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향 그래프 연결
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        DFS(start, 0);
        System.out.println(result);
    }

    public static void DFS(int index, int cnt) {
        visited[index] = true;

        for (int i: graph.get(index)) {
            if (!visited[i]) {
                if (i == end) {
                    result = cnt+1;
                    return;
                }
                DFS(i, cnt+1);
            }
        }
    }
}
