package DataStructure;

import java.io.IOException;

public class PGM68935 {
    public static void main(String[] args) throws IOException {
        int solution = solution(125);
        System.out.println(solution);
    }

    public static int solution(int n) {
        int answer = 0;
        String ternary = toTernary(n);
        answer = toDecimal(ternary);
        return answer;
    }

    public static String toTernary(int n) {
        if (n == 0) return "0";
        String result = "";
        while (n > 0) {
            result = (n%3) + result;
            n /= 3;
        }

        return new StringBuilder(result).reverse().toString();
    }

    public static int toDecimal(String n) {
        int result = 0;
        char[] charArray = n.toCharArray();
        for (int i=charArray.length - 1; i>=0; i--) {
            int num = Integer.parseInt(String.valueOf(charArray[i]));
            result += (int) (num * Math.pow(3, charArray.length - 1 - i));
        }

        return result;
    }
}
