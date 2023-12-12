package assignment4;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Miro {

    // bfs

    public static int minimumMove(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        int [][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int []> queue = new ArrayDeque<>();
        // 시작 지점 (x, y, distance)
        queue.offer(new int[] {0, 0, 1});

        while (!queue.isEmpty())  {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            // 출구에 가장 빠르게 도달할 수 있는 경로의 칸 수
            int count = current[2];

            if (x == n-1 && y == m-1) {
                // 최단 경로 반환
                return count;
            }

            // 상하좌우 이동
            for (int[] dir: dirs) {
                int dx = x + dir[0];
                int dy = y + dir[1];

                // 방문하지 않은 경우
                if (0 <= dx && dx < n && 0 <= dy && dy < m && maze[dx][dy] == 1) {
                    maze[dx][dy] = 0; // 방문
                    queue.offer(new int[]{dx, dy, count+1});
                }
            }
        }

        // 도착 지점 못가는 경우
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] arr = new int[10][10];
        int row = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }

            String[] boxes = line.split(" ");
            for (int col = 0; col < boxes.length; col++) {
                arr[row][col] = Integer.parseInt(boxes[col]);
            }

            row++;
        }

        int result = minimumMove(arr);
        System.out.println(result);
    }
}



//        Scanner scanner = new Scanner(System.in);

//        int[][] arr = new int[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                arr[i][j] = scanner.nextInt();
//            }
//        }
//
//        int result = shortestPath(arr);
//        System.out.println("Shortest path length: " + result);

