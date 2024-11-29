package Bellman_Ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ11657 {
    // 최단 경로 정답 배열
    static long[] D;
    // 에지 리스트 배열
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        D = new long[N+1];
        edges = new Edge[M+1];

        // 최단 거리 D 배열 초기화
        Arrays.fill(D, Integer.MAX_VALUE);

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작지점
            int A = Integer.parseInt(st.nextToken());
            // 도착지점
            int B = Integer.parseInt(st.nextToken());
            // 걸리는 시간 (가중치)
            int C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        // 벨만 포드
        D[1] = 0;
        // N-1만큼 반복
        for (int i=1; i<N; i++) {
            for (int j=0; j<M; j++) {
                Edge edge = edges[j];
                // 벨만 포드 공식 (더 작은 최단 거리가 있을 때 업데이트)
                if (D[edge.start] != Integer.MAX_VALUE && D[edge.end] > D[edge.start] + edge.weight) {
                    D[edge.end] = D[edge.start] + edge.weight;
                }
            }
        }

        // 음수 사이클 존재 여부 판독
        boolean isCycle = false;

        // 마지막 음수 사이클 확인
        for (int i=0; i<M; i++) {
            Edge edge = edges[i];
            if (D[edge.start] != Integer.MAX_VALUE && D[edge.end] > D[edge.start] + edge.weight) {
                D[edge.end] = D[edge.start] + edge.weight;
                isCycle = true;
            }
        }

        if (isCycle) {
            // 음수 사이클 있을 때
            System.out.println(-1);
        } else {
            // 1번 도시에서 출발하여 N번 도시까지 가는 가장 빠른(최단) 거리 시간 출력
            for (int i=2; i<=N; i++) {
                // 만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력
                if (D[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(D[i]);
                }
            }
        }
    }

    static class Edge implements Comparator<Edge> {

        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compare(Edge o1, Edge o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    }
}
