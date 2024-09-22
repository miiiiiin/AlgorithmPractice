package DataStructure;

import java.util.Scanner;
import java.util.Stack;

public class BOJ10773 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int[] arr = new int[k];

        int result = 0;

        for (int i=0; i<k; i++) {
            arr[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<k; i++) {
            if (arr[i] == 0) {
                stack.pop();
            } else {
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        System.out.println(result);
    }
}
