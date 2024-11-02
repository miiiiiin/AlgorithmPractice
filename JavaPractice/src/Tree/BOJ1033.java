package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1033 {
    public static class BOJ1167 {

        static ArrayList<Node>[] arr;
        static boolean[] visited;
        // 트리의 지름 저장하는 배열
        static int[] distanceArr;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 트리의 정점 개수
            int V = Integer.parseInt(st.nextToken());
            arr = new ArrayList[V+1];
            visited = new boolean[V+1];
            distanceArr = new int[V+1];

            for (int i=1; i<=V; i++) {
                // 인접리스트 초기화
                arr[i] = new ArrayList<>();
            }

            for (int i=1; i<=V; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                while (true) {
                    int m = Integer.parseInt(st.nextToken());
                    if (m == -1) break;
                    int distance = Integer.parseInt(st.nextToken());
                    arr[n].add(new Node(m, distance));
                }
            }

            // 임의의 노드 1에서 가장 먼 노드를 찾는다. 찾은 노드를 node 변수에 저장
            BFS(1);

            // 가장 먼 노드 저장
            int max = 1;

            // 가장 거리가 먼 노드 찾기
            for (int i=2; i<=V; i++) {
                if (distanceArr[max] < distanceArr[i]) {
                    max = i;
                }
            }

            // 방문&거리 저장 배열 리셋
            visited = new boolean[V+1];
            distanceArr = new int[V+1];

            // node에서 부터 가장 먼 노드까지의 거리를 구한다.
            // 가장 큰 값을 가지는 node를 시작점으로 지정
            BFS(max);
            Arrays.sort(distanceArr);
    //        System.out.println(Arrays.toString(distanceArr));
            System.out.println(distanceArr[V]);
        }

        public static void BFS(int idx) {

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(idx);
            visited[idx] = true;

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (Node i : arr[curr]) {
                    int node = i.m;
                    int distance = i.distance;
                    if (!visited[node]) {
                        visited[node] = true;
                        // 거리 배열 업데이트
                        queue.add(node);
                        distanceArr[node] = distanceArr[curr] + distance;
                    }
                }
            }
        }
        private static class Node {
            int m; // 연결하는 정점
            int distance;

            public Node(int m, int distance) {
                this.m = m;
                this.distance = distance;
            }

            public int getM() {
                return this.m;
            }

            public int getDistance() {
                return this.distance;
            }
        }
    }
}
