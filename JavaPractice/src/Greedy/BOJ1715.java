package Greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int sum = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
            queue.offer(arr[i]);
        }

        while (queue.size() > 1) {
            int first = queue.remove();
            int second = queue.remove();
            sum += first + second;
            queue.offer(first + second);
        }
        System.out.println(sum);
    }
}
