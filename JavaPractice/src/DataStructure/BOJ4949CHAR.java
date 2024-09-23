package DataStructure;

import java.util.Scanner;

public class BOJ4949CHAR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s;

        while (true) {
            s = sc.nextLine();

            if (s.equals(".")) break;
            System.out.println(match(s));
        }
    }

    public static String match(String s) {
        // 배열을 스택처럼 사용
        char[] stack = new char[s.length()];
        int size = 0;

        for (char i: s.toCharArray()) {

            switch (i) {
                case '[':
                    stack[size++] = i;
                    break;
                case '(':
                    stack[size++] = i;
                    break;
                case ')':
                    // 스택이 비어있거나 스택의 top이 소괄호 (와 매치가 안될 경우
                    if (size == 0 || stack[size-1] != '(')
                        return "no";
                    else
                        size--;
                    break;
                case ']':
                    // 스택이 비어있거나 스택의 top이 대괄호 [와 매치가 안될 경우
                    if (size == 0 || stack[size-1] != '[')
                        return "no";
                    else
                        size--;
                    break;

                default:
                    break;
            }
        }
        if (size == 0) {
            return "yes";
        } else {
            return "no";
        }

    }
}
