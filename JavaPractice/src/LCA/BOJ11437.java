package LCA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ11437 {

    static ArrayList<Integer>[] tree;
    // 부모 노드 저장 배열
    static int[] parent;
    // 깊이 저장 배열
    static int[] depth;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 노드의 개수
        int N = sc.nextInt();

        tree = new ArrayList[N+1];
        visited = new boolean[N+1];
        parent = new int[N+1];
        depth = new int[N+1];

        // tree 인접 리스트 초기화
        for (int i=0; i<=N; i++) {
            tree[i] = new ArrayList<>();
        }

        // tree 인접 리스트에 그래프 데이터 저장
        for (int i=0; i<N-1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        // BFS 탐색해서 부모 노드와 깊이 저장
        BFS(1);

        // 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M
        int M = sc.nextInt();
        for (int i=0; i<M; i++) {
            // 공통 조상을 구할 두 노드
            int u = sc.nextInt();
            int v = sc.nextInt();
            // M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력
            int lca = LCA(u, v);
            System.out.println(lca);
        }
    }

    private static int LCA(int i, int j) {
        if (depth[i] < depth[j]) {
            // i와 j를 맞추기 위해 서로 교환
            int temp = i;
            i = j;
            j = temp;
        }

        // 두 노드의 depth 맞추기
        while (depth[i] != depth[j]) {
            // i의 부모 노드로 이동하여 depth 맞추기
            i = parent[i];
        }

        // 같은 조상 나올 때까지 부모 노드 한 칸씩 올림
        // i와 j가 같아질 때까지 부모 노드로 이동
        while (i != j) {
            i = parent[i];
            j = parent[j];
        }
        return i;
    }

    private static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visited[node] = true;

        // depth 레벨
        int level = 1;
        // 깊이 크기
        int now_size = 1;
        // 트리의 방문 수
        int count = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : tree[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    // 부모 노드 저장
                    parent[next] = curr;
                    // 노드 depth 저장
                    depth[next] = level;
                }
            }
            count++;
            // 이번 높이에 해당하는 모든 노드를 방문했을 때
            if (count == now_size) {
                // 트리 방문 수 0으로 초기화
                count = 0;
                // 깊이 크기를 현재 큐의 크기로 초기화
                now_size = queue.size();
                // 현재 배열의 depth 1씩 증가
                level++;
            }
        }
    }
}
