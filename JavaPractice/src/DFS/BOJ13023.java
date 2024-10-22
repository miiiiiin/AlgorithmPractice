package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ13023 {

    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static boolean arrived;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람의 수
        int N = Integer.parseInt(st.nextToken());
        // 친구 관계의 수
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        visited = new boolean[N+1];
        arrived = false;

        for (int i=0; i<=N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i=0; i<=N; i++) {
            DFS(i, 1);
            if (arrived) break;
        }

        if (arrived) System.out.println(1);
        else System.out.println(0);
    }

    public static void DFS(int node, int depth) {
        if (depth == 5 || arrived) {
            arrived = true;
            return;
        }
        visited[node] = true;
        for (int i : arr[node]) {
            if (!visited[i]) {
                DFS(i, depth+1);
            }
        }
        visited[node] = false;
    }
}
