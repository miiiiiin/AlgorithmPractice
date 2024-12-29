package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AS_OPTIMIZING {
    static int[] parent;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        pq = new PriorityQueue<>();
        int vSum = 0;

        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(u, v, c));
        }

        int edgeConnect = 0;

        while (!pq.isEmpty()) {
            Node data = pq.poll();
            if (data == null) break;
            if (find(data.s) != find(data.e)) {
                union(data.s, data.e);
                edgeConnect++;
                vSum += data.v;
            }
        }

        if (edgeConnect == N-1) {
            System.out.println(vSum);
        } else {
            System.out.println(-1);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a!=b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static class Node implements Comparable<Node> {
        int s, e, v;
        Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.v, o.v);
        }
    }
}


