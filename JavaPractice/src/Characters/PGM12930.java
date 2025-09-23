package Characters;

import java.io.IOException;
import java.util.Arrays;

public class PGM12930 {

    public static void main(String[] args) throws IOException {
        String solution = solution("try hello world");
        System.out.println(solution);
    }

    public static String solution(String s) {
        String answer = "";
        String[] arr = s.split("");
        System.out.println(Arrays.toString(arr));

        StringBuilder builder = new StringBuilder();

        int idx = 0;
        for (String str : arr) {
            if (str.equals(" ")) {
                idx = 1;
            }
            if (idx % 2 == 0) {
                builder.append(String.valueOf(str).toUpperCase());
            } else {
                builder.append(String.valueOf(str).toLowerCase());
            }
            idx++;
        }

        answer = builder.toString();
        return answer;
    }
}
