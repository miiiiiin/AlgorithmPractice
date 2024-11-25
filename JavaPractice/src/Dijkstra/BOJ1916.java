package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
    // 방문 배열 리스트
    static boolean[] visited;
    // 인접 리스트 배열 저장 리스트
    static ArrayList<Node>[] list;
    // 최소 비용 저장(최단 경로) 배열
    static int[] costs;
    // 우선순위 큐
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 개수
        int N = Integer.parseInt(br.readLine());
        // 버스의 개수
        int M = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];
        list = new ArrayList[N+1];
        costs = new int[N+1];

        Arrays.fill(costs, Integer.MAX_VALUE);
        for (int i=0; i<=N; i++) {
            // 인접 리스트 초기화
            list[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 버스 출발 도시 번호
            int u = Integer.parseInt(st.nextToken());
            // 도착지 도시 번호
            int v = Integer.parseInt(st.nextToken());
            // 출발지부터 도착지까지의 버스 비용
            int w = Integer.parseInt(st.nextToken());
            // u에서 v(간선에 가중치 포함) 연결
            list[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        // 출발점 도시번호와 도착점 도시번호 입력
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int result = dijkstra(start, end);
        System.out.println(result);
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작점 우선순위 큐에 추가
        pq.offer(new Node(start, 0));
        // 시작점 노드 배열 0으로 설정
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currVertex = curr.end;

            if (visited[currVertex]) continue;
            visited[currVertex] = true;

            for (Node n : list[currVertex]) {
                if (!visited[n.end]) {
                    int next = n.end;
                    int value = n.weight;
                    // 다음 노드 배열값 > 현재 선택된 노드 + 다음 비용일때 값 업데이트
                    if (costs[next] > costs[currVertex] + value) {
                        costs[next] = costs[currVertex] + value;
                        pq.add(new Node(next, costs[next]));
                    }
                }
            }
        }
        // 도착지점의 최소 비용 반환
        return costs[end];
    }

    static class Node implements Comparable<Node> {
        int end, weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
