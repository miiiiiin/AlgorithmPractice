package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2606_Graph {

    static int n, m, result;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        visited = new boolean[n+1];

        for (int i=0; i<=n; i++) {
            // 그래프 초기화
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 그래프 간 양방향 연결
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        Arrays.fill(visited, false);
        BFS(1, 0); // 탐색 시장할 정점 번호
        System.out.println(result);
    }

    public static void BFS(int index, int cnt) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        //        System.out.println("index = " + index);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            //            System.out.println("curr = " + curr);
            for (int i: graph.get(curr)) {
                if (!visited[i]) {
                    queue.add(i);
                    //                    System.out.println("i = " + i);
                    visited[i] = true;
                    result += 1;
                }
            }
        }
    }
}
