package DataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ11866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        sb.append("<");

        for (int i=1; i<=n; i++) {
            queue.add(i);
        }

        // N명이 모두 제거될때까지 반복
        while (queue.size() > 1) {

            for (int i=1; i<k; i++) {
                // K-1 번째 까지의 숫자를 큐 뒤로 이동
                int tmp = queue.poll();
                queue.add(tmp);
            }
            // K번째 사람 제거하고 출력
            int item = queue.poll();
            sb.append(item).append(", ");
        }

        // 큐가 비지 않았을 경우, 나머지 수들을 출력해줌
        while (!queue.isEmpty()) {
            int item = queue.poll();
            sb.append(item);
        }

        sb.append(">");
        System.out.println(sb);
    }
}
