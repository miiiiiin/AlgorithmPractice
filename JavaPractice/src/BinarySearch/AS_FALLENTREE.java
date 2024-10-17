package BinarySearch;

import java.util.Scanner;

/**
 * 문제 분석
 * H 높이에서 자르고 남은 나무 부분의 합이 최소 M 이상이 되는 H의 최댓값을 구해야 함.
 * 그래서 자를 수 있는 최대 높이 H를 찾아 그 높이 이상인 나무들을 잘라 얻은 목재의 양이 M이상이 되도록 해야함.
 * 나무의 높이는 최대 1,000,000까지이므로, 가능한 모든 높이에 대해 순차적으로 탐색하는 것은
 * 시간이 오래 걸리므로 이분 탐색을 사용하여 얻은 목재의 양이 M을 만족하고
 * 최대로 자를 수 있는 가장 높은 높이 H를 찾아야 함.
 *
 * 1. 자르는 높이 H가 커질수록 얻는 목재의 양이 감소한다.
 * 2. 특정 H에서 필요한 목재 양을 만족하는지 여부를 기준으로 이분 탐색을 적용
 */

/** 구현 방법
 * 1. 이분 탐색을 위한 인덱스인 low와 high를 설정한다. high는 주어진 나무들 높이 중 최댓값으로 설정한다.
 * 2. low와 high의 중앙값을 구한다.
 * 3. mid 높이에서 자른 나무들로부터 얻는 목재의 양을 계산
 * 4. 각각의 나무 높이가 mid보다 크면, (나무 높이 - mid)로 목재를 얻을 수 있다.
 * 5. 나무 높이가 mid보다 작으면, 자르지 않는다.
 * 6. 얻을 수 있는 목재 양이 M 이상일 때, 더 높은 H로 이동시킨다
 * 7. 얻을 수 있는 목재 양이 M 미만일 때, 더 낮은 H로 이동시킨다
 * 8. 종료 조건 : low와 high가 교차하는 시점에 가장 높은 높이 H를 찾는다.
 */

/**
 * 시간 복잡도
 * 이분 탐색 과정은 O(log H)이다. H는 나무의 최대높이로 최대 1,000,000까지의 값을 가질 수 있다.
 * 각 이분 탐색마다 getWoodAmount() 함수로 나무 높이들의 배열을 순회하며 자르고 남은 목재의 양을
 * 계산하므로 O(N)의 시간이 소요된다.
 * 전체 시간 복잡도는 O(N log H)이다.
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
