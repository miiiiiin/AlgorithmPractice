package Characters;

import java.io.IOException;
import java.util.Arrays;

public class PGM12932 {
    public static void main(String[] args) throws IOException {
        int[] solution = solution(12345);
        System.out.println(Arrays.toString(solution));
    }
    public static int[] solution(long n) {
        int[] answer = {};
        String str = Long.toString(n);
        answer = new int[str.length()];

        for (int i=0; i<str.length(); i++) {
            answer[i] = str.charAt(str.length() - 1 - i) - '0';
        }
        return answer;
    }
}
