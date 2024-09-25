package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1863ARRAY {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // 건물의 개수
        int result = 0;

        // y 높이 저장할 배열
        int[] y = new int[n];

        // 스택으로 건물 높이 관리 (새로 들어오는 높이가 달라지면, stack에 push해서 y의 높이를 저장하여 새 건물로 갱신)
        // 현재 높이와 스택의 높이 비교하여 최소 건물의 수 추정
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            //  첫 번째 지점의 x좌표는 항상 1이다.
            int x = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());


            // 건물의 높이(y)가 낮아지면 뒤에 있는 건물이 끝났다는걸 의미
            // 건물 높이 y가 stack의 top보다 작으면 stack에서 pop
            while (!stack.isEmpty() && stack.peek() > y[i]) {
                stack.pop();
            }

            // 0을 stack에 push하지 않기 위해 y[i] != 0이어야 함.
            if (y[i] != 0 && (stack.isEmpty() || stack.peek() < y[i])) {
                result++;
                stack.push(y[i]);
            }
        }
        System.out.println(result);
    }
}
