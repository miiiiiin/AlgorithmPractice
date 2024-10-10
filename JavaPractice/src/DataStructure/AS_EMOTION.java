package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현 방법
 * 1. 첫 번째 초기 윈도우 크기만큼의 합을 계산하여 그 평균값이 정수인지를 확인하고 정수이면 평균값을 출력한다.
 *
 * 2. K번부터 대화에서 사용된 단어의 개수 N까지의 반복문을 통해 슬라이딩 윈도우를 수행한다.
 *
 * 3. N개의 감정 점수가 1 2 3 4 5 이고, K=3인 경우를 예로 들면, 본격적으로 슬라이딩 윈도우를 수행하기 위해
 * 반복문을 돌렸을 때, 먼저 2부터 4까지의 합을 구해야 한다. 그러기 위해 슬라이딩 윈도우의 범위를 조정해야 하는데
 * 이전의 sum값인 6에서 첫 번째 감정 점수인 1을 빼고, 그 다음 감정 점수인 4를 더해야 한다.
 * 맨 첫 번 째 감정 점수인 1을 빼기 위해서는 0번째 인덱스를 구해야 하는데, i(3) - K(3) = 0 이므로
 * 이 공식을 이용해 맨 왼쪽 인덱스를 구할 수 있다. 그렇게 한 칸 전의 감정 점수를 빼고,
 * 한 칸 후의 감정 점수를 더하는 형식으로 슬라이딩 윈도우 범위를 유지하며 sum값을 구할 수 있다.
 *
 * 4. 반복문 내에서 슬라이딩 윈도우 범위를 유지하며 1번과 같이 진행한다.
 * 5. 단, 모든 윈도우의 평균이 정수가 아닌 경우엔 -1을 출력 (boolean 플래그 값을 두어 체크한다.)
 */

/**
 * 시간 복잡도 분석
 * 1. 첫번째 윈도우의 합 구할 경우, K개의 감정 점수를 모두 더하므로 O(K)
 * 2. 슬라이드 윈도우에서 한 칸 씩 빼고, 더하는 연산이 K부터 시작하여 N까지 반복하므로 O(N-K)
 * 그리하여 최종 시간 복잡도는 O(N)으로 정리할 수 있다.
 */
public class AS_EMOTION {

    // 모든 윈도우의 평균이 정수가 아닌 여부 체크
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 대화에서 사용된 단어의 개수
        int N = Integer.parseInt(br.readLine());

        // 감정 점수
        int[] scoreArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            scoreArr[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 윈도우 크기
        int K = Integer.parseInt(br.readLine());

        // 윈도우 합
        long sum = 0;

        // 초기 슬라이딩 윈도우 합 갱신
        for (int i=0; i<K; i++) {
            sum += scoreArr[i];
        }

        // 첫 번째 슬라이딩 윈도우 범위에 있는 점수의 평균값이 정수이면 출력
        scoreCheck(sum, K);

        // 슬라이딩 윈도우 수행
        for (int i=K; i<N; i++) {
            // j는 맨 왼쪽 인덱스, i는 맨 오른쪽 인덱스
            // 슬라이딩 윈도우 범위를 유지하며 i,j가 한 칸씩 이동
            int j = i - K;
            sum += scoreArr[i];
            sum -= scoreArr[j];

            scoreCheck(sum, K);
        }

        // 모든 윈도우의 평균이 정수가 아닐 경우
        if (!flag) System.out.println(-1);
    }

    public static void scoreCheck(long sum, int K) {
        if (sum % K == 0) {
            flag = true;
            System.out.println(sum / K);
        }
    }
}
