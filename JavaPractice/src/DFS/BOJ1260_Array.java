package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_Array {

    static int n, m, v;
    static int[][] arr;
    static boolean[] visited;
    static String dfsOrder;
    static String bfsOrder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        visited = new boolean[n+1];
        dfsOrder = "";
        bfsOrder = "";

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 노드 간 양방향 연결
            arr[x][y] = arr[y][x] = 1;
//            System.out.println("arr check:" + Arrays.toString(arr[x]));
        }

        Arrays.fill(visited, false);
        DFS(v);
        System.out.println(dfsOrder);
        Arrays.fill(visited, false);
        BFS(v);
        System.out.println(bfsOrder);
    }

    public static void DFS(int node) {
        visited[node] = true;
        dfsOrder += node + " ";
        for (int i=1; i<=n; i++) {
            // 방문 조건: 서로 양방향으로 연결되어 있고, 이미 방문하지 않은 곳
            if (arr[node][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }
    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        bfsOrder += node + " ";

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i=1; i<=n; i++) {
                if (arr[curr][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    bfsOrder += i + " ";
                }
            }
        }
    }
}
