package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // 건물의 개수
        int result = 0;

        Stack<Integer> stack = new Stack<>();

        // 빌딩의 높이 y를 push (x는 1부터 시작, i=1일 때 y[i] = 1임)
        // 건물 높이가 top과 같으면 같은 빌딩이기때문에 stack 넣을 필요 x
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());


            // 스택이 비어있지 않고, 스택의 top > y일 경우,
            // 건물 하나 증가, stack pop
            // 건물의 높이가 낮아지면 뒤에 있는 건물이 끝났다는걸 의미하기 때문에 Stack에서 pop하면서 건물의 개수 1 증가
            while (!stack.isEmpty() && stack.peek() > y) {
                result++;
                stack.pop();
            }

            // 스택 비었거나 top < y[i] 면 빌딩 수 증가 (y의 높이가 top보다 높아지면 뒤에 건물이 하나 더 있다는 의미)
            // y의 높이가 높아졌으므로 stack에 push하여 제일 높은 건물로 갱신
            // 0을 stack에 push하지 않기 위해 y != 0이어야 함.
            while (y != 0 && (stack.isEmpty() || stack.peek() < y)) {
                // 빌딩의 높이 y를 push
                stack.push(y);
            }
        }

        while (!stack.isEmpty()) {
            // 남은 건물이 있다는 의미. 남은 스택 수만큼 건물 갯수 증가
            if (stack.peek() > 0) result++;
            stack.pop();
        }
        System.out.println(result);
    }
}
