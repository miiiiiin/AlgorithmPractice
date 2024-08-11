package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

// 인접리스트
public class BOJ2606_ArrayList {

    static int n, m, result;
    static ArrayList<Integer> [] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        arr = new ArrayList[n+1];

        for (int i=0; i<=n; i++) {
            // 인접 리스트 초기화
            arr[i] = new ArrayList<Integer>();
        }

        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향 연결
            arr[x].add(y);
            arr[y].add(x);
        }

        Arrays.fill(visited, false);
        BFS(1, 0); // 탐색 시장할 정점 번호
        System.out.println(result);
    }

    public static void BFS(int index, int cnt) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            System.out.println("Arrays.toString(curr) = " + Arrays.toString(arr) + " " + curr);
            for (int i: arr[curr]) {
                // 방문 조건: 아직 방문하지 않은 상태일 때
                System.out.println("i = " + i);
                if (!visited[i]) {
                    result += 1;
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        
    }
}
