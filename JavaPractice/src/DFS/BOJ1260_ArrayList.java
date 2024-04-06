package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260_ArrayList {
    static int n, m, v;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    static String dfsOrder;
    static String bfsOrder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        dfsOrder = "";
        bfsOrder = "";
        graph = new ArrayList<>();
        visited = new boolean[n+1];

        for (int i=0; i<=n; i++) {
            // 그래프 초기화
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 그래프 간 양방향 연결
            graph.get(x).add(y);
            graph.get(y).add(x);

            Collections.sort(graph.get(x));
            Collections.sort(graph.get(y));

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
        for (int i: graph.get(node)) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfsOrder += curr + " ";

            for (int i: graph.get(curr)) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}