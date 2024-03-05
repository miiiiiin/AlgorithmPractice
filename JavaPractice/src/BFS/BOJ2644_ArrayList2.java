package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2644_ArrayList2 {

    static int n, start, end, res = -1, dist[];
    static ArrayList<Integer> relation[];


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 전체 사람 수
        n = Integer.parseInt(br.readLine());
        // 촌수 배열
        dist = new int [n+1];
        // 촌수 관계 정보
        relation = new ArrayList[n+1];

        for (int i=1; i<=n; i++) {
            relation[i] = new ArrayList<Integer>();
        }

        Arrays.fill(dist, -1);


        System.out.println("dist:" + Arrays.toString(relation) + " " + Arrays.toString(dist));


        st = new StringTokenizer(br.readLine(), " ");

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 간선 개수
        int m = Integer.parseInt(br.readLine());

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 인접 노드 양방향 연결 (인접리스트 만들기)
            relation[x].add(y);
            relation[y].add(x);
        }

        System.out.println("relation:" + Arrays.toString(relation));

        BFS(start, end);
        System.out.println(res);
    }

    public static void BFS(int start, int end) {
        Queue<Integer> queue = new LinkedList<Integer>();
        // start부터 출발
        dist[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {

            // 현재 확인할 사람
            int curr = queue.poll();

            // 비교 대상 찾으면 촌수 dist에 저장
            if (curr == end) {
                res = dist[curr];
                System.out.println("res dist:" + res + " " + Arrays.toString(dist) + " " + curr);
                break;
            }

            // 해당 사람과 관계있는 사람들 확인
            for (int i=0; i<relation[curr].size(); i++) {
                int tmp = relation[curr].get(i);

                // 방문 조건 : 아직 촌수 확인/방문하지 않은 상태일 때
                if (dist[tmp] != -1) continue;

                dist[tmp] = dist[curr] + 1;
                queue.add(tmp);

                System.out.println("bfs dist:" + Arrays.toString(dist));
            }
        }

    }
}
