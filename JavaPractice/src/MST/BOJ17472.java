package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17472 {
    static int[][] map;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited;
    static int N, M;
    // 대표 노드 저장 배열
    static int[] parent;
    static int sNum;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        parent = new int[N+1];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 분리
        sNum = 1;
        sumlist = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                BFS(i, j);
                sNum++;
                sumlist.add(mlist);
            }
        }



    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mlist = new ArrayList<>();
        int[] start = {i, j};
        queue.offer(start);
        mlist.add(start);
        visited[i][j] = true;

        map[i][j] = sNum;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] dir : dirs) {
                // 상하좌우로 이동한 row, col의 좌표
                int dx = curr[0] + dir[0];
                int dy = curr[0] + dir[1];

                // 방문 조건 : 배열 넘어가면 안됨
                if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
                    // 좌표가 0이면 안되고, 방문한 적 없어야 함
                    if (map[dx][dy] != 0 && !visited[dx][dy]) {
                        addNode(dx, dy, queue);
                    } else { break; }
                }
                if (dir[0] < 0) dir[0]--;
                else if (dir[0] > 0) dir[0]++;
                else if (dir[1] < 0) dir[1]--;
                else if (dir[1] > 0) dir[1]++;
            }

        }
    }

    private static void addNode(int i, int j, Queue<int[]> queue) {
        map[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = {i, j};
        mlist.add(temp);
        queue.add(temp);
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
