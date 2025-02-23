package Combination;

import java.io.*;
import java.util.*;

/** 구현 방법
 * 1. 그래프는 인접 리스트 형태로 구성하고, BFS 방식을 통하여 최단 거리를 구한다.
 *  1.1 각 도시에서 출발하여 다른 도시까지의 최단 거리를 구하는데 사용되며, 각 노드에서 다른 도시까지의 최단 거리를 dist 배열에 저장한다.
 * 2. 순열 생성
 *  2.2 mandatory에 주어진 필수 방문 도시들을 포함하여 모든 순열을 생성 (K! 번 수행)
 * 3. 경로 계산
 *  3.1 각 순열에 대해 방문 순서대로 도시 간의 최단 거리를 합산하여 총 최단 경로를 계산
 *  3.2 계산 도중 도시 간 연결이 없는 경우(dist[u][v] == -1), 그 순열은 무효로 처리
 *  3.3 currentDistance: 현재 순열에서 방문하는 경로의 누적 거리를 저장.
 *  3.4 valid: 현재 순열의 경로가 유효한지 확인하는 플래그
 *
 * 4. 순열 순회
 * 4.1 순열의 각 도시를 순서대로 탐색하며 두 도시 사이의 최단 거리를 dist[from][to]로 가져옴
 * 4.2 누적 거리 currentDistance와 현재까지의 최소 거리 minDistance를 비교하여 더 작은 값으로 업데이트
 *
 * 5. 결과
 *  5.1 모든 순열을 확인한 후, 최단 경로를 출력
 *  5.2 방문이 불가능한 경우에는 -1을 출력
 *  5.3 K == 1인 경우, 필수 방문 도시는 이미 방문된 상태이므로 최단 거리가 0임을 출력
 */

/** 시간복잡도
 * 1. BFS는 각 도시마다 한 번씩 수행되며, 각 BFS는 O(N + M) 시간이 걸림
 * 2. 순열 생성은 K! 번 수행 : O(K!) (K개의 필수 방문 도시 순열 생성)
 * 3. 전체 시간 복잡도는 O(K! * (N + M))
 */
public class AS_CITY_EXPLORATION {
    static int N, M, K;
    static ArrayList<ArrayList<Integer>> graph;
    // 도시 간 최단 거리 저장하는 인접행렬
    static int[][] dist;
    static ArrayList<Integer> mandatory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시 개수
        N = Integer.parseInt(st.nextToken());
        // 도로 개수
        M = Integer.parseInt(st.nextToken());
        // 필수 방문 도시 개수
        K = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 필수 방문 도시 입력
        st = new StringTokenizer(br.readLine());
        mandatory = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            mandatory.add(Integer.parseInt(st.nextToken()));
        }

        // 특수 케이스: 필수 방문 도시가 1개일 경우 탐험 거리 = 0
        if (K == 1) {
            System.out.println(0);
            return;
        }

        // 도시 간 최단 거리 계산
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], -1);
            BFS(i);
        }

        // 모든 필수 방문 도시의 최단 경로 계산
        int result = findShortestPath();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    // BFS를 사용하여 도시 간 최단 거리 계산
    private static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        boolean[] visited = new boolean[N + 1];
        visited[node] = true;
        // 자기 자신까지의 거리는 항상 0 (출발점에서 출발점까지의 거리 초기화)
        dist[node][node] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 현재 노드와 연결된 노드들 순회
            for (int i: graph.get(curr)) {
                if (!visited[i]) {
                    visited[i] = true;
                    // 현재 칸에서 1씩 추가하여 다음번 방문하게 되는 칸의 값 갱신
                    dist[node][i] = dist[node][curr] + 1;
                    queue.add(i);
                }
            }
        }
    }

    // 모든 필수 방문 도시를 방문하는 최단 거리 계산
    private static int findShortestPath() {
        int[] cities = new int[K];
        for (int i = 0; i < K; i++) {
            cities[i] = mandatory.get(i);
        }

        int minDistance = Integer.MAX_VALUE;
        // 필수 방문 도시들의 순열 생성
        List<List<Integer>> permutations = generatePermutations(cities);

        for (List<Integer> perm : permutations) {
            int currentDistance = 0;
            boolean valid = true;

            for (int i = 0; i < perm.size() - 1; i++) {
                int from = perm.get(i);
                int to = perm.get(i + 1);

                if (dist[from][to] == -1) {
                    valid = false;
                    break;
                }
                currentDistance += dist[from][to];
            }

            if (valid) {
                minDistance = Math.min(minDistance, currentDistance);
            }
        }
        return minDistance;
    }

    // 순열 생성
    private static List<List<Integer>> generatePermutations(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        permute(array, 0, result);
        return result;
    }

    // 순열 생성 : array 배열의 가능한 순열 조합하여 생성
    private static void permute(int[] array, int start, List<List<Integer>> result) {
        if (start == array.length) {
            List<Integer> perm = new ArrayList<>();
            for (int num : array) {
                perm.add(num);
            }
            result.add(perm);
            return;
        }

        for (int i = start; i < array.length; i++) {
            swap(array, start, i);
            permute(array, start + 1, result);
            swap(array, start, i);  // Backtrack
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
