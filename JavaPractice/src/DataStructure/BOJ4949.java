package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 문자열 s의 길이가 N이라면 시간 복잡도: O(N)
        String s;

        while (true) {
            s = br.readLine();
            if (s.equals(".")) break;
            System.out.println(match(s));
        }
    }

    public static String match(String s) {
        Stack<Character> stack = new Stack<>();

        char[] c = s.toCharArray();

        for (char i: c) {

            switch (i) {
                case '[':
                    stack.push(i);
                    break;
                case '(':
                    stack.push(i);
                    break;
                case ')':
                    // 스택이 비어있거나 스택의 top이 소괄호 (와 매치가 안될 경우
                    if (stack.empty() || stack.peek() != '(')
                        return "no";
                    else
                        stack.pop();
                    break;
                case ']':
                    // 스택이 비어있거나 스택의 top이 대괄호 [와 매치가 안될 경우
                    if (stack.empty() || stack.peek() != '[')
                        return "no";
                    else
                        stack.pop();
                    break;

                default:
                    break;
            }
        }
//        System.out.println("stack = " + stack.toString());
        if (stack.isEmpty()) {
            return "yes";
        }
        else {
            return "no";
        }
    }
}
