package BinarySearch;

import java.util.Scanner;

/**
 * 구현 방법
 * 1. H 높이에서 자르고 남은 나무 부분의 합이 최소 M 이상이 되는 H의 최댓값
 *
 */

/**
 * 시간 복잡도
 */
public class AS_FALLENTREE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 나무의 개수
        int N = sc.nextInt();
        // 필요한 목재의 양
        int M = sc.nextInt();
        // 나무의 높이 저장하는 배열
        int[] heights = new int[N];

        // 각 나무 별 얻은 목재의 양 저장하는 배열
        // 자른 나무의 남은 부분(나무의 높이 - H)의 합이 바로 얻은 목재 양
        int[] woods = new int[N];

        for (int i=0; i<N; i++) {
            heights[i] = sc.nextInt();
        }

        // 이분 탐색 위한 start, end 인덱스
        int low = 0;
        // (최적) 목재 양 M을 만족하면서도 자른 나무의 높이가 가능한 한 높도록 설정한 높이 H를 찾아야 한다.
        int high = 0;

        for (int height: heights) {
            // 나무들의 높이 중 최대값 갱신
            high = Math.max(high, height);
        }

        // 더 이상 탐색할 구간이 없을 때까지 실행 (시작 인덱스가 더 작거나 같을때까지 반복)
        while (low<=high) {
            // 나무들 높이의 중앙값
            int mid_value = (low + high) / 2;

            int gotWoodAmount = getWoodAmount(heights, mid_value);

            if (gotWoodAmount < M) {
                high = mid_value - 1;
            } else {
                // 현재 중앙값은 가능한 H 중 하나
                // 목재가 M 이상이면 더 큰 높이를 시도
                low = mid_value + 1;
            }
        }
        System.out.println(high);
    }

    public static Integer getWoodAmount(int[] heights, int H) {
        int wood = 0;
        // 나무들의 높이에서 H만큼 자르고 얻은 목재의 양 계산
        for (int height : heights) {
            // 만약 나무의 높이가 H 이하라면, 나무는 자르지 않는다.
            if (height < H) continue;
            // 자른 나무의 남은 부분(나무의 높이 - H)의 합이 얻은 목재 양
            wood += height - H;
        }
        return wood;
    }
}
