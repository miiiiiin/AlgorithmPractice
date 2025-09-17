package Characters;

import java.io.IOException;

public class PGM12918 {
    public static void main(String[] args) throws IOException {
        boolean solution = solution("a234");
        System.out.println(solution);
    }

    public static boolean solution(String s) {
        boolean answer = true;
        if (s.length() == 4 || s.length() == 6) {
            char[] arr = s.toCharArray();
            for (char c : arr) {
                if (Character.isLetter(c)) {
                    answer = false;
                    return answer;
                } else if (Character.isDigit(c)) {
                    answer = true;
                }
            }
        } else {
            answer = false;
        }
        return answer;
    }

    // 다른 풀이법
//    public static boolean solution(String s) {
//        if (s.length() == 4 || s.length() == 6) {
//            try {
//                int onlyInteger = Integer.parseInt(s);
//                return true;
//            } catch (NumberFormatException e) {
//                return false;
//            }
//        } else return false;
//    }

//    public boolean solution(String s) {
//        if (s.length() == 4 || s.length() == 6) return s.matches("(^[0-9]*$)");
//        return false;
//    }
}