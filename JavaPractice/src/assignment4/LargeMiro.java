package assignment4;

import java.util.*;

public class LargeMiro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter start coordinates (x y): ");
        int[] start = {scanner.nextInt(), scanner.nextInt()};

        System.out.println("Enter exit coordinates (x y): ");
        int[] exit = {scanner.nextInt(), scanner.nextInt()};

//        System.out.println("Enter the number of traps: ");
//        int trapCount = scanner.nextInt();
//        int[][] traps = new int[trapCount][2];



//        System.out.println("Enter trap coordinates (x y): ");
//        for (int i = 0; i < trapCount; i++) {
//            traps[i][0] = scanner.nextInt();
//            traps[i][1] = scanner.nextInt();
//        }
//        System.out.println("traps: " + Arrays.toString(traps));

//        Set<String> traps = new HashSet<>();
//        while (true) {
//            String line = scanner.nextLine().trim();
//            if (line.isEmpty()) {
//                break;
//            }
//            traps.add(line);
//        }

        int[][] traps = {{0, 1}, {1, 0}};

        int result = isPossible(traps, start, exit);
        System.out.println(result);
    }

    private static int isPossible(int[][] traps, int[] start, int[] exit) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int n = 1000000; // 미로의 크기

        Queue<int[]> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start[0] + "," + start[1]);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == exit[0] && y == exit[1]) {
                return 1; // 탈출구에 도달
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited.contains(nx + "," + ny)) {
                    boolean isValidMove = true;

                    for (int[] trap : traps) {
                        if (trap[0] == nx && trap[1] == ny) {
                            isValidMove = false; // 함정에 걸림
                            break;
                        }
                    }

                    if (isValidMove) {
                        queue.offer(new int[]{nx, ny});
                        visited.add(nx + "," + ny);
                    }
                }
            }
        }

        return 0; // 탈출 불가능
    }
}
