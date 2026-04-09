package Characters;

import java.io.IOException;
import java.util.Arrays;

public class PGM12951 {

    public static void main(String[] args) throws IOException {
        String solution = solution("3people unFollowed me");
        System.out.println(solution);
    }
    public static String solution(String s) {
        String[] strs = s.split(" ");
        System.out.println("strs: " + Arrays.toString(strs));
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<strs.length; i++) {
            String word = strs[i];
            System.out.println("str: " + word);
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                System.out.println("lowercase: " + word.substring(1));
                sb.append(word.substring(1).toLowerCase());
            }

//            System.out.println("check length: " + strs.length - 1);
            if (i < strs.length - 1) sb.append(" ");
        }
        String answer = sb.toString();
        return answer;
    }
}
