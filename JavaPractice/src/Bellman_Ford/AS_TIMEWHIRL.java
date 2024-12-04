package Bellman_Ford;

import java.util.Arrays;
import java.util.Scanner;

/** 구현 방법
 *  문제의 요구사항에서 시간 왜곡이 일어나는 도로가 존재하는지 여부를 확인하라는 문장이 나와서 벨만 포드로 시도해보았다.
 *  1. 그래프 구성
 *  1.1 입력은 그래프의 정점(V)과 간선(E)의 정보로 구성
 *  1.2 간선의 가중치는 음수일 수 있으며, 음수 가중치 사이클(시간 왜곡 고리)이 있는지 확인해야 함
 *  1.3 정답 리스트 배열 : 모든 정점의 최단 거리를 무한대로 초기화
 *  1.4 간선 리스트 배열 : 간선은 Edge 객체로 표현되며, 이는 start(시작 정점), end(도착 정점), weight(가중치) 정보를 포함
 *
 *  2. 벨만 포드 알고리즘 수행
 *  2.1 그래프의 최단 경로를 계산하면서 음수 가중치 사이클을 탐지
 *  2.2 시작 정점의 거리를 0으로 설정 D[1] = 0
 *  2.3 모든 간선에 대해 (V-1)번 반복하며 최단 거리를 갱신
 *  2.4 갱신 조건
 *      2.4.1 D[edge.start] != Integer.MAX_VALUE: 시작 정점이 도달 가능한 경우
 *      2.4.2 D[edge.end] > D[edge.start] + edge.weight: 도착 정점까지의 기존 거리가 더 긴 경우
 *      2.4.3 위 조건 일 때, D[edge.end]를 D[edge.start] + edge.weight로 갱신한다
 *  2.5 (V-1)번의 반복 이후, 한 번 더 간선들을 확인하여 거리 갱신이 발생하면 음수 사이클이 존재한다고 판단
 */

/** 시간복잡도
 *
 * 1. 초기화 단계 : O(V + E)
 * 2. 최단 거리 갱신 단계 : O((V−1)⋅E)
 * 3. 음수 사이클 확인 단계 : O(E)
 *
 * 전체 시간 복잡도 O(V * E) (V: 정점 수, E: 간선 수)
 * 주어진 제약사항(V ≤ 1000, E ≤ 10000)에 대해 효율적으로 동작
 */

public class AS_TIMEWHIRL {
    // 정답 배열
    static int[] D;
    // 에지 리스트 배열
    static Edge[] edges;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정점의 수
        int V = sc.nextInt();
        // 간선의 수
        int E = sc.nextInt();

        D = new int[V+1];
        edges = new Edge[V+1];

        Arrays.fill(D, Integer.MAX_VALUE);

        for (int i=0; i<E; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            // 간선 가중치 (음수일 수도 있음)
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w);
        }

        // 벨만 포드 수행
        D[1] = 0;
        // V-1만큼 반복
        for (int i=1; i<=V; i++) {
            for (int j=0; j<E; j++) {
                Edge edge = edges[j];

                if (D[edge.start] != Integer.MAX_VALUE && D[edge.end] > D[edge.start] + edge.weight) {
                    D[edge.end] = D[edge.start] + edge.weight;
                }
            }
        }

        boolean isCycle = false;

        // V-1 반복 이후, D배열 업데이트 되어서 음수 사이클 존재하는지 확인
        for (int i=0; i<E; i++) {
            Edge edge = edges[i];
            if (D[edge.start] != Integer.MAX_VALUE && D[edge.end] > D[edge.start] + edge.weight) {
                D[edge.end] = D[edge.start] + edge.weight;
                isCycle = true;
            }
        }

        if (isCycle) {
            // 시간 왜곡이 일어나는 도로가 존재한다면
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static class Edge {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
