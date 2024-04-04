package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    static int n, m, v;
    static ArrayList<Integer>[] arr;
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    static String dfsOrder;
    static String bfsOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        dfsVisited = new boolean[n+1];
        bfsVisited = new boolean[n+1];
        dfsOrder = "";
        bfsOrder = "";

        for (int i=1; i<=n; i++) {
            // 인접리스트 초기화
            arr[i] = new ArrayList<Integer>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);

            // 오름차순 정렬
            Collections.sort(arr[x]);
            Collections.sort(arr[y]);
        }

//        System.out.println(Arrays.toString(arr));
        DFS(v);
        BFS(v);
        System.out.println(dfsOrder);
        System.out.println(bfsOrder);
    }

    public static void DFS(int index) {
        dfsOrder += index + " ";
        if (dfsVisited[index]) {
            return;
        }
        dfsVisited[index] = true;
        for (int i: arr[index]) {
            if (!dfsVisited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        bfsVisited[node] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfsOrder += curr + " ";

            for (int i: arr[curr]) {
                // 방문 조건 : 현재 노드와 다음 노드가 연결된 상태이고 아직 방문하지 않은 상태일 때
                if (!bfsVisited[i]) {
                    bfsVisited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
