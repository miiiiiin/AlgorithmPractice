package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Simulation
public class BOJ1063 {
    // 방향 벡터 (좌우상하,왼상대,우상대,왼하대,우하대)
    static final int[] dr = {0, 0, 1, -1, -1, -1, 1, 1};
    static final int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};
    static String[] directions = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        String K = input[0];
        String S = input[1];
        int N = Integer.parseInt(input[2]);

        // 킹과 돌 초기 좌표
        // 열: 'A' → 0, 'B' → 1
        int kx = 8 - (K.charAt(1) - '0');
        // 열: 'A' → 0, 'B' → 1
        int ky = K.charAt(0) - 'A';
        int sx = 8 - (S.charAt(1) - '0');
        int sy = S.charAt(0) - 'A';

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            // 방향 명령 인덱스
            int dir = 0;
            for (int j = 0; j < directions.length; j++) {
                if (directions[j].equals(line)) {
                    dir = j;
                    break;
                }
            }

            // 킹 이동한 위치
            int newKx = kx + dr[dir];
            int newKy = ky + dc[dir];

            // 킹 이동 범위 조건 검사
            if (!isValid(newKx, newKy)) continue;

            // 돌과 위치가 같다면 돌도 같은 방향으로 한 칸 이동
            if (newKx == sx && newKy == sy) {
                int newSx = sx + dr[dir];
                int newSy = sy + dc[dir];

                // 이동 범위 검사
                if (isValid(newSx, newSy)) {
                    // 돌 이동
                    sx = newSx;
                    sy = newSy;
                } else {
                    continue;
                }
            }

            // 킹 위치 이동
            kx = newKx;
            ky = newKy;
        }
        System.out.println((char) (ky + 'A') + "" +  (8 - kx));
        System.out.println((char) (sy + 'A') + "" + (8 - sx));
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}