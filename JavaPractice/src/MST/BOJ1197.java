package MST;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1197 {
    // union-find 대표 노드 배열 저장
    static int[] parent;
    static int answer;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // 정점의 개수
        int V = sc.nextInt();
        // 간선의 개수
        int E = sc.nextInt();

        parent = new int[V+1];
        answer = 0;
        pq = new PriorityQueue<>();

        // 대표 노드 배열 자기 자신으로 초기화
        for (int i=1; i<V+1; i++) {
            parent[i] = i;
        }

        for (int i=0; i<E; i++) {
            // 정점
            int A = sc.nextInt();
            int B = sc.nextInt();
            // 가중치
            int C = sc.nextInt();
            pq.add(new Node(A, B, C));
        }

        int edgeConnect = 0;

        while (edgeConnect <= V-1) {
            Node data = pq.poll();
            // 에지 시작점과 끝 점의 부모 노드가 다르면 (사이클 생기지 않으면)
            if (data == null) break;
            if (find(data.getS()) != find(data.getE())) {
                union(data.getS(), data.getE());
                answer += data.getV();
                edgeConnect++;
            }
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }
    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            // 경로 압축
            return parent[a] = find(parent[a]);
        }
    }

    static class Node implements Comparable<Node> {
        int s,e,v;

        Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        public int getS() {
            return s;
        }

        public int getE() {
            return e;
        }

        public int getV() {
            return v;
        }

        // 가중치 v 기준 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
}

