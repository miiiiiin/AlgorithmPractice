package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
public class BOJ1260 {
    static int n, m, v;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static String dfsOrder;
    static String bfsOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new boolean[n+1];
        dfsOrder = "";
        bfsOrder = "";

        for (int i=1; i<m; i++) {
            // 인접리스트 초기화
            arr[i] = new ArrayList<Integer>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);
        }

        System.out.println(Arrays.toString(arr));
        DFS(v);
        System.out.println(dfsOrder);
    }

    public static void DFS(int index) {
        dfsOrder += index + " ";
        System.out.println(index);
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        for (int i: arr[index]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int index) {

    }
}
