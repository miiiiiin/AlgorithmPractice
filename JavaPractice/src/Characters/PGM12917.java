package Characters;

import assignment4.Workerholic.Task;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PGM12917 {
    public static void main(String[] args) throws IOException {
        String solution = solution("Zbcdefg");
        System.out.println(solution);
    }

    public static String solution(String s) {
        String answer = "";
        Character[] arr = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        for (Character c : arr) {
            answer += c;
        }

        return answer;
    }
}
