package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11724RE {

    // 인접리스트 어레이 리스트
    static ArrayList<Integer>[] arr;
    // 방문 표시 배열
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new boolean[n+1];

        // 연결요소 개수
        int answer = 0;

        // 인접리스트 초기화
        for (int i=0; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u].add(v);
            arr[v].add(u);
        }

        for (int i=1; i<=n; i++) {
            if (!visited[i]) {
                BFS(i);
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        visited[node] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i : arr[curr]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
