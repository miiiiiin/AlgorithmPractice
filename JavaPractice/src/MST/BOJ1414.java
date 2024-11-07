package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1414 {
    static int[] parent;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        pq = new PriorityQueue<>();
        // 랜선 길이의 총합 저장
        int sum = 0;
        // 에지 가중치 합
        int vSum = 0;
        // 에지 연결 개수
        int edgeConnect = 0;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();
            for (int j=0; j<N; j++) {
                // j번째 문자가 0인 경우는 컴퓨터 i와 컴퓨터 j를 연결하는 랜선이 없음을 의미
                int temp = 0;
                // 소문자일 때
                if (c[j] >= 'a' && c[j] <= 'z') {
                    temp = c[j] - 'a' + 1;
                    // 대문자일 때
                } else if (c[j] >= 'A' && c[j] <= 'Z') {
                    temp = c[j] - 'A' + 27;
                }
                // 총 랜선 길이 저장
                sum = sum + temp;
                if (i != j && temp != 0) {
                    pq.add(new Node(i, j, temp));
                }
            }
        }

        for (int i=1; i<N+1; i++) {
            parent[i] = i;
        }
        while (!pq.isEmpty()) {
            Node data = pq.poll();
            // 시작점과 끝 점의 대표 노드가 다르면 (사이클 생기지 않으면)
            if (find(data.s) != find(data.e)) {
                union(data.s, data.e);
                // 두 연결된 정점 사이(사이클 존재x)의 에지 가중치 합 저장
                vSum += data.v;
                edgeConnect++;
            }
        }

        if (edgeConnect == N-1) {
            // 기부할 수 있는 랜선 길이의 최댓값 (랜선 길이 총합 - 에지 가중치 합)
            int cal = sum - vSum;
            System.out.println(cal);
        } else {
            // 모든 컴퓨터가 연결되어 있지 않으면
            System.out.println(-1);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
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
        int s,e,v;

        Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
}
