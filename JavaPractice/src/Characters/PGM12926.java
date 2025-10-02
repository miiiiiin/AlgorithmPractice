package Characters;

import java.io.IOException;

public class PGM12926 {

    public static void main(String[] args) throws IOException {
        String solution = solution("a B z", 4);
        System.out.println(solution);
    }

    public static String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        String answer = "";
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == ' ') { sb.append(' '); }
            else if (Character.isLowerCase(c)) {
                /*
                 * [소문자 순환 로직]
                 * 1. ch - 'a': 현재 문자를 0('a')부터 시작하는 인덱스(0-25)로 변환시킴
                 * 2. + n: 밀 거리를 더함
                 * 3. % 26: 알파벳 개수인 26으로 나눈 나머지를 취하여 'z'를 넘어갈 경우 'a'로 다시 돌아오도록 순환시킴
                 * 4. + 'a': 0부터 시작하는 인덱스를 다시 ASCII 문자로 변환
                 */
                char converted = (char) ('a' + (c -'a' + n) %  26);
                sb.append(converted);

            } else if (Character.isUpperCase(c)) {
                // 대문자 처리 : 소문자와 동일. 기준 문자만 'A'로
                char converted = (char) ('A' + (c - 'A' + n) % 26);
                sb.append(converted);
            }
            answer = sb.toString();
        }
        return answer;
    }
}