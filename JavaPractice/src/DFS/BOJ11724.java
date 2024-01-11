package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 연결 요소의 개수 구하기
public class BOJ11724 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1]; // 0번째 인덱스 사용하지 않기 위해 n+1로 늘림
        // 그래프 데이터 저장 인접 리스트
        A = new ArrayList[n+1];

        // 인접리스트 초기화
        for (int i=1; i<n+1; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 비방향이므로 시작 -> 종료, 종료 -> 시작 연결이 가능
            // 양쪽 방향으로 add
            A[u].add(v);
            A[v].add(u);
        }

        System.out.println(Arrays.toString(A));
        int count = 0;

        for (int i=1; i<n+1; i++) {
            if (!visited[i]) {
                count++; // 현재 방문하지 않은 노드이면 연결요소의 개수를 증가해줌
                // 현재 방문하지 않은 노드를 시작점으로 dfs 실행
                dfs(i);
            }
        }

        System.out.println(count);
    }

    private static void dfs(int cur_node) {
        if (visited[cur_node]) {
            return;
        }
        // 방문한 노드가 아니면 방문 표시를 해줌
        visited[cur_node] = true;

        // 현재 노드에서 연결되어 있는 노드들을 모두 탐색
        for (int i : A[cur_node]) {
            // 탐색하지 않은 노드가 존재한다면 그를 기준/시작점으로 다시 dfs 실행
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
