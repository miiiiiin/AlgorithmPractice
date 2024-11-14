package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17472 {
    static int[][] map;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int N, M;
    // 대표 노드 저장 배열
    static int[] parent;
    static int sNum;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 분리
        sNum = 1;
        sumlist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    BFS(i, j);
                    sNum++;
                    sumlist.add(mlist);
                }
            }
        }

        pq = new PriorityQueue<>();

        for (int i = 0; i < sumlist.size(); i++) {
            ArrayList<int[]> curr = sumlist.get(i);
            for (int j = 0; j < curr.size(); j++) {
                int r = curr.get(j)[0];
                int c = curr.get(j)[1];
                int curr_S = map[r][c];

                for (int k = 0; k < 4; k++) {
                    // 상하좌우로 이동한 row, col의 좌표
                    int row = dr[k];
                    int col = dc[k];
                    int blength = 0;
//                    int dx = r + row;
//                    int dy = c + col;
                    // 방문 조건 : 배열 넘어가면 안됨
                    while (r + row >= 0 && r + row < N && c + col >= 0 && c + col < M) {
                        // 같은 섬이면 에지 생성 불가능
                        System.out.println("check map :" + map[r+row][c+col] + ", currs:" + curr_S);
                        if (map[r+row][c+col] == curr_S) {
                            break;
                        } else if (map[r+row][c+col] != 0) {
                            // 같은 섬도 아니고 바다도 아니면
                            // 다른 섬 -> 길이가 1 이상일 때 에지로 더함
                            System.out.println("row: " + r+row + ", col :" + c+col);
//                            System.out.println("dx: " + dx + ", dy: " + dy);
                            System.out.println("blength:" + blength);
                            System.out.println("map:" + map[r+row][c+col]);
                            if (blength > 1) {
                                pq.add(new Node(curr_S, map[r+row][c+col], blength));
                            }
                            break;
                        } else {
                            blength++;
                        }
                        if (row < 0) row--;
                        else if (row > 0) row++;
                        else if (col < 0) col--;
                        else if (col > 0) col++;
                    }
                }
            }
        }
        // 섬 개수만큼 초기화
        parent = new int[sNum];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int useEdge = 0;
        int result = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result = result + now.v;
                useEdge++;
            }
        }
        if (useEdge == sNum - 2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
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

            int x = curr[0];
            int y = curr[1];
            for (int k = 0; k < 4; k++) {
                // 상하좌우로 이동한 row, col의 좌표
                int row = dirs[k][0];
                int col = dirs[k][1];
                // 방문 조건 : 배열 넘어가면 안됨
                while (x + row >= 0 && x + row < N && y + col >= 0 && y + col < M) {
                    // 좌표가 0이면 안되고, 방문한 적 없어야 함
                    if (map[x+row][y+col] != 0 && !visited[x+row][y+col]) {
                        addNode(x + row, y + col, queue);
                    } else { break; }
                    if (row < 0) row--;
                    else if (row > 0) row++;
                    else if (col < 0) col--;
                    else if (col > 0) col++;
                }
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
        int s, e, v;

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
