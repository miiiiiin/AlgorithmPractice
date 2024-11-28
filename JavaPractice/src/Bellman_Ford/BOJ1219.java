package Bellman_Ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1219 {
    // 최대 이익 (정답 배열)
    static long[] D;
    // 도시에서의 수입 배열
    static long[] income;
    // 에지 리스트 배열
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시 개수
        int N = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        // 교통 수단의 개수
        int M = Integer.parseInt(st.nextToken());

        D = new long[N];
        income = new long[N+1];
        edges = new Edge[M+1];

        // 최대 이익 저장 배열 초기화
        Arrays.fill(D, Long.MIN_VALUE);

        for (int i=0; i<M; i++) {
            // 교통 수단의 정보 입력 (시작, 끝, 가격)
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, price);
        }

        // 도시에서의 수입 배열 입력 (오민식이 각 도시에서 벌 수 있는 돈의 최댓값이 0번 도시부터 차례대로 주어진다)
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            income[i] = Long.parseLong(st.nextToken());
        }

        // 변형된 벨만 포드 수행
        D[startCity] = income[startCity];
        // 양수 사이클 전파되도록 충분히 큰 수로 반복
        for (int i=0; i<=N+100; i++) {
            for (int j=0; j<M; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].weight;

                // 출발 노드가 방문하지 않은 노드이면 스킵
                if (D[start] == Long.MIN_VALUE) continue;
                // 출발 노드가 양수 사이클에 연결된 노드라면 종료 노드도 연결 노드로 업데이트
                else if (D[start] == Long.MAX_VALUE) {
                    D[end] = Long.MAX_VALUE;
                } else if (D[end] < D[start] - price + income[end]) {
                    // 더 많은 돈을 벌 수 있는 새로운 경로가 발견됐을 때, 새로운 경로값으로 업데이트
                    // 여기에서 응용된 벨만포드 공식
                    // D[edge.end] < D[edge.start] - edge.weight + income[edge.end] 일 때, 업데이트 수행
                    D[end] = D[start] - price + income[end];
                    // N-1 반복 이후 업데이트되는 종료 노드는 양수 사이클 연결 노드로 변경 (양수 사이클 존재 판독)
                    // N-1 반복 횟수 이후 갱신되면 사이클에 연결되어있다는 뜻이므로 무한대값으로 갱신
                    if (i >= N-1)
                        D[end] = Long.MAX_VALUE;
                }
            }
        }

        if (D[endCity] == Long.MIN_VALUE) {
            // 도착 도시에 도착하는 것이 불가능할 때 (-무한대일 때)
            System.out.println("gg");
        } else if (D[endCity] == Long.MAX_VALUE) {
            // 도착 도시 D의 값이 무한대면 무한히 많이 벌 수 있다 (도착 도시에 도착했을 때 돈을 무한히 많이 가지고 있을 수 있다면)
            System.out.println("Gee");
        } else {
            // 도착 도시에 도착할 때, 가지고 있는 돈의 액수의 최댓값
            System.out.println(D[endCity]);
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
