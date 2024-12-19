package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398 {
    static int[] parent;
    static PriorityQueue<Node> pq;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        arr = new int[N+1][N+1];
        pq = new PriorityQueue<>();

        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (i != j && arr[i][j] != 0) {
                    pq.add(new Node(i, j, arr[i][j]));
                }
            }
        }

        int edgeConnect = 0;
        long vSum = 0;
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
