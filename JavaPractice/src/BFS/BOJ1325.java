package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1325 {

    static ArrayList<Integer>[] arr;
    // 신뢰도 배열 저장 (노드별 방문된 횟수 저장하는 배열)
    static int[] trustArr;
    static boolean[] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점(컴퓨터의 개수)
        int N = Integer.parseInt(st.nextToken());
        // 간선
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        trustArr = new int[N+1];
        visited = new boolean[N+1];

        // 인접 리스트 초기화
        for (int i=0; i<=N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 양방향 연결 아님
            // A가 B를 신뢰한다 (A는 B에게 해킹당할 수 있음)
            arr[A].add(B);
        }

        for (int i=1; i<N+1; i++) {
            // 방문 배열 초기화
//            Arrays.fill(visited, false);
            visited = new boolean[N+1];
            DFS(i);
//            BFS(i);
        }

        for (int i=1; i<=N; i++) {
            // 신뢰도 배열 저장 값 중에서 max 값 찾기
            if (max < trustArr[i]) {
                max = trustArr[i];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i=1; i<=N; i++) {
            // 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호 출력
            // max 값이 가장 큰 신뢰도 배열의 인덱스(노드) 출력
            if (trustArr[i] == max) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    public static void DFS(int node) {
        visited[node] = true;
        for (int i : arr[node]) {
            if (!visited[i]) {
                // i가 해킹할 수 있는 숫자 증가
                trustArr[i]++;
//                DFS(i);
                BFS(i);
            }
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int i : arr[curr]) {
                if (!visited[i]) {
                    trustArr[i]++;
                    queue.add(i);
                }
            }
        }
    }
}
