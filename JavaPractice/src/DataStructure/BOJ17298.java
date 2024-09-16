package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// 시간 복잡도 O(N)
public class BOJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 수열 배열
        int[] arr = new int[n];
        // 오큰수 저장 배열
        int[] result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        // 처음에는 항상 스택이 비어있음. 최초값으로 초기화
        stack.push(0);

        for (int i=1; i<n; i++) {
            System.out.println("stack = " + stack.peek() + ", arr stack = " + arr[stack.peek()]);
            System.out.println("arr[i] = " + arr[i]);
            // 현재 스택이 비어있지 않고, 현재 수열 값이 top에 해당하는 수열보다 클 때까지
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                System.out.println("============arr i = " + i + ", arr[i] = " + arr[i] + ", stacktop = " + stack.peek());
//                int pop = stack.pop();
//                System.out.println("result = " + result[pop]);
//                result[pop] = arr[i];
                // 각 index에 대해 최대 1번의 pop
                result[stack.pop()] = arr[i];
//                System.out.println("pop = " + pop + ", result[pop] = " + result[pop]);

                // 정답 배열에 오큰수 현재 수열로 저장
                System.out.println("result = " + Arrays.toString(result));
            }
            // 현재 수열을 스택에 push (각 index에 대해 최대 1번 push)
            stack.push(i);
        }

        // 스택이 비어 있지 않다면 빌 때까지 (스택에 남은 index 값들은 오큰수가 없음)
        while (!stack.isEmpty()) {
            System.out.println("stack string = " + stack.toString());
            int pop = stack.pop();
            System.out.println("empty = " + pop);
            result[pop] = -1;
        }

       StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            sb.append(result[i] + " ");
        }

        System.out.println(sb);
    }
}
