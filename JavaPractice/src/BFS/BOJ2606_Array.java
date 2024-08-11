package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606_Array {

    static int n, m, result;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];
        visited = new boolean[n+1];

        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향 연결
            arr[x][y] = arr[y][x] = 1;
        }

        Arrays.fill(visited, false);
        BFS(1, 0);
        System.out.println(result);
    }

    public static void BFS(int index, int cnt) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int i=1; i<=n; i++) {
                // 방문 조건
//                System.out.println("Arrays.toString(arr[curr]) + \" \" + curr = " + Arrays.toString(arr[curr]) + " " + curr);
//                System.out.println("arr[curr][i] + \", i:\" + i = " + arr[curr][i] + ", i:" + i);
//                System.out.println("visited check = " + visited[i]);
                if (!visited[i] && arr[curr][i] == 1) {
                    queue.add(i);
//                    System.out.println("i = " + i);
                    visited[i] = true;
                    result += 1;
                }
            }
        }
    }
}
