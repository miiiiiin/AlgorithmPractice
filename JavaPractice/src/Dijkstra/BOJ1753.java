package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {
    static String INF = "INF";
    static boolean[] visited;
    // 최단 경로 저장하는 배열
    static int[] distance;
    static PriorityQueue<Edge> pq;
    // 그래프 정보 저장 위한 인접 리스트
    static ArrayList<Edge>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 정점의 개수
        int V = Integer.parseInt(st.nextToken());
        // 간선의 개수
        int E = Integer.parseInt(st.nextToken());
        // 시작 정점의 번호
        int K = Integer.parseInt(br.readLine());

        visited = new boolean[V+1];
        distance = new int[V+1];
        list = new ArrayList[V+1];
        pq = new PriorityQueue<>();

        for (int i=1; i<=V; i++) {
            // 인접 리스트 초기화
            list[i] = new ArrayList<>();
        }

        // 최단 경로 배열 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // u에서 v로 가는 간선의 가중치
            int w = Integer.parseInt(st.nextToken());
            // 한 정점에서 w의 가중치를 가진 v를 간선으로 연결
            // u에서 v로 가는 간선의 가중치
            list[u].add(new Edge(v, w));
        }


        // 다익스트라 알고리즘 수행
        // 시작정점 우선순위 큐에 삽입
        pq.offer(new Edge(K, 0));
        // 시작점 노드의 최단 경로 배열 0으로 설정
        distance[K] = 0;
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            // 큐에서 꺼낸 노드와 연결된 노드 중, 아직 방문하지 않았고, 가중치 값이 가장 작은 노드 선택
            // 현재 정점
            int c_v = curr.vertex;
            // 이미 방문한 적이 있는 노드는 큐에 넣지 않음

            if (visited[c_v]) continue;
            visited[c_v] = true;

            for (int i=0; i<list[c_v].size(); i++) {
                // 현재 노드에서 i번째 연결 노드 가져오기
                Edge tmp = list[c_v].get(i);
                int next = tmp.vertex;
                int value = tmp.weight;

                // 공식 통해 업데이트 되는지 확인, 업데이트 되면 우선순위큐에 삽입
                // [연결 노드 거리 리스트 값] 보다 [선택 노드의 거리 리스트 값 + 에지 가중치]가 더 작은 경우 업데이트 수행
                // 최소거리로 업데이트
                if (distance[next] > distance[c_v] + value) {
                    distance[next] = value + distance[c_v];
                    pq.add(new Edge(next, distance[next]));
                }
            }

        }
        for (int i=1; i<=V; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println(INF);
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex, weight;

        Edge(int vertex, int value) {
            this.vertex = vertex;
            this.weight = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}

