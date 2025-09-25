package Characters;

import java.io.IOException;
import java.util.stream.IntStream;

public class PGM147335 {

    public static void main(String[] args) throws IOException {
        int solution = solution("3141592", "271");
        System.out.println(solution);
    }

    public static int solution(String t, String p) {
        //         int answer = 0;
//         for (int i=0; i<t.length(); i++) {

//         배열 범위 벗어나는 경우를 방지하기 위해 중간에 멈춤
//             if (i + p.length() > t.length()) { return answer; }
//             else {
//                 String substring = t.substring(i, i+p.length());
//                 if (Integer.parseInt(substring) <= Integer.parseInt(p)) {
//                     answer++;
//                 }
//             }

//         }
        // return answer;

        var pLength = p.length();
        var pValue = Long.parseLong(p);

        // Stream API 적용 (함수형 방식) => 처음부터 가능한 범위만 계산
        /**
         * 1. IntStream.rangeClosed(0, t.length() - pLength)
         * - 기존 코드: for (int i=0; i<t.length(); i++)에서 if (i + p.length() > t.length()) return answer;로 조기 종료
         * - Stream: 처음부터 유효한 범위만 생성 (0부터 t.length() - pLength까지)
         * - 예시: t="3141592", p="271" → 범위: 0, 1, 2, 3, 4 (총 5개)
         *
         - i=0: substring(0, 3) → "314"
         - i=1: substring(1, 4) → "141"
         - i=2: substring(2, 5) → "415"
         - i=3: substring(3, 6) → "159"
         - i=4: substring(4, 7) → "592"
         *
         *
         * 2. .mapToObj(i -> t.substring(i, i+pLength))
         * - 기존 코드: String substring = t.substring(i, i+p.length());
         * - Stream: 각 인덱스 i를 부분 문자열로 변환
         * - 결과: ["314", "141", "415", "159", "592"]
         *
         * 3. .filter(num -> num <= pValue)
         * - 기존 코드: if (Integer.parseInt(substring) <= Integer.parseInt(p)) { answer++; }
         * - Stream: 조건을 만족하는 숫자만 필터링
         * - pValue = 271이므로 → [314, 141, 415, 159, 592] 중에서 271 이하인 값들만
         *
         * 참고 : 오버플로우 방지를 위해 Integer 대신 Long으로 파싱
         *
         *
         */
        return (int) IntStream.rangeClosed(0, t.length() - pLength)
            .mapToObj(i -> t.substring(i, i+pLength))
            .mapToLong(Long::parseLong)
            .filter(num -> num <= pValue)
            .count();
    }
}
