package Implementation;

import java.util.Scanner;

public class BOJ1913 {

    static int[][] map;
    // 위, 왼, 아, 오
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int target = sc.nextInt();
        map = new int[N][N];

        // 시작 번호
        int num = 1;
        // 시작 좌표 (중심 위치)
        int x = N/2;
        int y = N/2;

        // 초기 방향
        int dir = 0;
        // 현재 움직이는 칸 수
        int step = 1;

        // 타겟 target 좌표 초기화
        int targetX = x;
        int targetY = y;

        // 달팽이 방향으로 수 채우기
        while (num <= N * N) {
                for (int j=0; j<step; j++) {
                    // 숫자 채우기
                    map[x][y] = num;

                    // 타겟 숫자일 경우, 좌표 저장
                    if (num == target) {
                        targetX = x + 1;
                        targetY = y + 1;
                    }
                    // 다음 숫자 업데이트
                    num++;

                    x = x + dr[dir];
                    y = y + dc[dir];

                    if (num > N*N) break;
                }
            // 방향 전환
            dir = (dir + 1) % 4;
            // 방향 전환 후 두 번마다 이동 칸 수 증가
            /**
             * 1부터 시작해서 1칸 이동 후, 방향 전환 => (3부터) step=2
             * 2칸 이동 후, 방향 전환 => (7부터) step=3
             * 3칸 이동 후, 방향 전환 => (10부터) step=4
             */
            if (dir == 0 || dir == 2) {
                step++;
            }
        }

        // 달팽이 배열 출력
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 타겟 출력
        System.out.println(targetX + " " + targetY);
    }
}
