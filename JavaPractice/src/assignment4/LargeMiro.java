package assignment4;

//import java.util.*;

import java.util.*;

public class LargeMiro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.println("Enter start coordinates (x y): ");
        int[] start = {scanner.nextInt(), scanner.nextInt()};

//        System.out.println("Enter exit coordinates (x y): ");
        int[] exit = {scanner.nextInt(), scanner.nextInt()};

//        System.out.println("Enter trap coordinates (x y) or press Enter to finish: ");
        Set<int[]> traps = new HashSet<>();
        while (true) {
            String scan = scanner.nextLine();
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {

                break;
            }
            String[] coordinates = line.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            traps.add(new int[]{x, y});
        }
//        Set<int[]> traps = new HashSet<>();
//        while (scanner.hasNextLine()) {
//            String scan = scanner.nextLine();
//            String line = scanner.nextLine();
//
//            System.out.println("linecheck:" + line + line.equals("\n") + line.length());
//
//            if (line.isEmpty()) {
//                break;
//            }
//            String[] coordinates = line.split(" ");
//            int x = Integer.parseInt(coordinates[0]);
//            int y = Integer.parseInt(coordinates[1]);
//
//            traps.add(new int[]{x, y});
//        }
//        System.out.println("traps: " + traps);
//        int result = isPossible(traps, start, exit);
        int result = isPossible(traps.toArray(new int[0][]), start, exit);
        System.out.println(result);
    }

    public static int isPossible(int[][] traps, int[] start, int[] exit) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}};
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
                return 0; // 탈출구에 도달하지 못함
            }

            // 휴리스틱: 출발점에서 도착점까지의  함정의 위치보다 크면 prune
            int diff = Math.abs(x - exit[0]) + Math.abs(y - exit[1]);
            if (diff > traps.length * 2) {
                return 1; // 탈출 가능
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

        // 탈출
        return 1;
    }
}
