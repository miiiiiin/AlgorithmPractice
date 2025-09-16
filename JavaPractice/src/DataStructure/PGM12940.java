package DataStructure;

import java.io.IOException;
import java.util.Arrays;

public class PGM12940 {

    public static void main(String[] args) throws IOException {


    }

    public int[] solution(int n, int m) {
        int[] answer = {};
        answer = Arrays.copyOf(answer, answer.length+2);

        // 최대 공약수 (n/m 중 큰 거)
        answer[0] = gcd(n, m);
        System.out.println(Arrays.toString(answer));

        // 최소 공배수
        answer[1] = lcm(n, m);
        return answer;
    }

    // 유클리드 호제 (최대 공약수)

    /**
     * 예: gcd(48, 18)
     * 48%18 = 12 (나머지) => 48 = 18 * 2(몫) + 12
     * 18%12 = 6 => 18 = 12 * 1(몫) + 6
     * 12%6 = 0 => 12 = 6 * 2(몫) + 0 => 나머지가 0이므로 종료
     *
     * 더 이상 나눌 수 없을 때(나머지가 0일 때), a보다 큰 공약수는 존재 X
     * 따라서 a가 최대 공약수
     */
    public int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a%b);
    }

    // 최소 공배수
    public int lcm(int a, int b) {
        // 3 * 12 / gcd (a, b)
        /**
         * 두 수의 모든 약수들이 최소공배수에 포함되어야 함
         * a × b = 모든 소인수의 지수 합
         * GCD × LCM = 모든 소인수의 (최소지수 + 최대지수)
         * a × b에서 중복된 공통부분이 GCD만큼 있음 => 중복 제거한 전체가 LCM(두 수가 만나는 첫번째 지점)
         */
        return (a*b) / gcd(a, b);
    }

}
