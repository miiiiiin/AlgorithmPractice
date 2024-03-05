package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2644_ArrayList {

    static int n, m, start, end;
    static int[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 정점의 갯수

        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine()); // 간선의 갯수

        graph = new ArrayList[n + 1];
        visited = new int[n + 1];

        for (int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 연결
        for (int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 인접 노드 간 양방향 연결
            graph[x].add(y);
            graph[y].add(x);
        }

        Arrays.fill(visited, -1);
        BFS(start);
        System.out.println(visited[end]);
    }

    static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = 0;

        while (!queue.isEmpty()) {
            index = queue.poll();

            for (int y: graph[index]) {

                // 이미 방문한 경우 계속
                if (visited[y] != -1) continue;

                queue.add(y);
                visited[y] = visited[index] + 1;
            }
        }
    }
}
