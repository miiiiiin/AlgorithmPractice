package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AS_OPTIMIZINGNETWORK {
    static int[] distance;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시 개수
        int N = Integer.parseInt(st.nextToken());
        // 고속도로 개수
        int M = Integer.parseInt(st.nextToken());
        // 제약 조건의 개수
        int K = Integer.parseInt(st.nextToken());

        distance = new int[N+1];
        list = new ArrayList[N+1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i=0; i<N; i++) {
            list[i] = new ArrayList<Node>();
        }

        for (int i=0; i<M; i++) {
            // 연결하려는 두 도시
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 고속도로 건설 비용
            int c = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, c));
        }

        for (int i=0; i<K; i++) {
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
        }

    }

    static class void dijkstra(int start, int end) {
        PriorityQueue
    }

    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
