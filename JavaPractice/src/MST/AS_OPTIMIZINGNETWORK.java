package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 구현 방식
 * 최소 비용으로 모든 도시를 연결해야 하므로 MST 알고리즘을 적용하였다.
 * 1. 배열 초기화
 *  1-1. 부모 배열을 자기 자신으로 초기화한다
 *  1-2. 우선순위 큐를 사용하여, 큐에 들어가는 정보들이 자동 정렬되도록 한다 (가중치 순 정렬)
 * 2. N개의 도시와 M개의 간선을 사용해 그래프 표현
 *  2-1. 시작, 끝, 가중치 정보를 담을 NODE 클래스를 만든다.
 *  2-2. Node는 각 간선을 나타내며, 가중치(비용) 기준으로 정렬하기 위해 Comparable 인터페이스를 채택한다
 *  2-2. 우선순위 큐에 노드 정보를 담는다
 * 3. MST 알고리즘 (크루스칼)
 *  3-1. 간선을 가중치 순으로 정렬하고, 간선을 하나씩 추가하면서 구성한다
 *  3-2. 유니온-파인드(Union-Find) 자료구조를 사용하여 경로를 압축시키고, 두 서로 다른 집합을 연결시킨다
 *  3-3. pq에서 비용 낮은 순으로 하나씩 꺼낸 간선 데이터의 두 정점 data.s와 data.e가 같은 집합에 속하지 않는다면,
 *  union으로 두 정점을 연결한다.(같은 집합으로 합친다)
 *  3-4. 간선 비용을 answer 변수에 누적하여 더하고, edgeConnect(간선 연결 횟수)를 1씩 증가시킨다
 * 4. 출력
 *  4-1. 모든 도시가 연결되기 위해 필요한 간선의 개수는 N-1이므로 edgeConnect == N-1에 도달하게 되면 최소 비용을 출력한다
 *  4-2. (간선이 부족하여 MST 완성 불가능일 경우) 모든 도시를 연결할 수 없다면 -1을 출력한다
 */

/** 시간복잡도
 * 1. 우선순위 큐 : 간선 정렬에 사용되는 알고리즘(병합정렬, 힙정렬)은 M(logM)의 시간복잡도를 가진다.
 * M은 간선의 개수이고, logM은 입력 데이터를 분할하고 병합하는 데 필요한 단계의 수이다.
 *
 * 2. 유니온 파인드 연산
 *  2-1. find & union 연산 : 경로압축 효과. 시간복잡도는 O(a(N)). a(N)은 역아커만 함수로, 거의 상수에 가까운 매우 느리게 증가하는 함수이다.
 *  2-2. M개의 간선에 대해 각 간선마다 유니온-파인드를 호출하므로 O(Ma(N))의 복잡도를 가진다
 *
 *  3. 총 시간복잡도
 *  O(MlogM + Ma(N)). a(N)은 상수로 간주되므로, 실제로는 O(MlogM)의 시간복잡도로 간주된다
 */
public class AS_OPTIMIZINGNETWORK {
    static int[] parent;
    static PriorityQueue<Node> pq;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시 개수
        int N = Integer.parseInt(st.nextToken());
        // 고속도로 개수
        int M = Integer.parseInt(st.nextToken());
        // 제약 조건의 개수
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        pq = new PriorityQueue<>();
        answer = 0;

        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 연결하려는 두 도시
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 고속도로 건설 비용
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(u, v, c));
        }

        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
        }

        int edgeConnect = 0;

        while (!pq.isEmpty()) {
            Node data = pq.poll();
            if (data == null) break;
            if (find(data.s) != find(data.e)) {
                union(data.s, data.e);
                answer += data.v;
                edgeConnect++;
            }
        }

        if (edgeConnect == N-1) {
            // 모든 도시를 연결할 수 있다면 최소 비용을 출력
            System.out.println(answer);
        } else {
            // 모든 도시를 연결할 수 없다면 -1을 출력
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
