package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260RE {

    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static String bfsOrder;
    static String dfsOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new boolean[n+1];
        dfsOrder = "";
        bfsOrder = "";

        // 인접 리스트 크기 초기화
        for (int i=0; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향 연결
            arr[x].add(y);
            arr[y].add(x);

            // 오름차순 정렬
            Collections.sort(arr[x]);
            Collections.sort(arr[y]);

        }

        DFS(v);
        Arrays.fill(visited, false);
        BFS(v);

        System.out.println(dfsOrder);
        System.out.println(bfsOrder);
    }

    public static void DFS(int idx) {
        visited[idx] = true;
        dfsOrder += idx + " ";
        for (int i : arr[idx]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int idx) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(idx);

        visited[idx] = true;

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            bfsOrder += curr + " ";

            for (int i: arr[curr]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
