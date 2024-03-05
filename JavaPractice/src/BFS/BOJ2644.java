package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ2644 {

    static int n, m, start, end;
    static int[][] arr;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        visited = new int[n+1];

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 서로 양방향으로 연결되어 있음을 의미
            arr[x][y] = 1;
            arr[y][x] = 1;
        }

        System.out.println("arr check:" + Arrays.toString(arr) + " " + Arrays.toString(visited));

        BFS(start);

        System.out.println(visited[end] == 0 ? -1 : visited[end]);
    }

    public static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();

        // 시작점 큐에 삽입
        queue.add(index);

        System.out.println("start queue:" + queue.toString());

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            System.out.println("curr queue:" + curr + " " + Arrays.toString(arr[curr]) + " " + Arrays.toString(visited));

            if (curr == end) break;

//            for (int[] map: arr) {
//                System.out.println("check arr:" + Arrays.toString(map));
//            }

            System.out.println("visited check:" + Arrays.toString(visited));


            for (int i=1; i<=n; i++) {
                // 방문 조건 : 현재 노드와 다음 노드가 연결된 상태이고 아직 방문하지 않은 상태일 때
                if (arr[curr][i] == 1 && visited[i] == 0) {
                    queue.add(i);

                    System.out.println("added queue:" + i + " " + queue.toString());

                    visited[i] = visited[curr] + 1;

                    System.out.println("visited:" + visited[i] + " " + visited[curr]);
                }
            }
        }
    }
}
