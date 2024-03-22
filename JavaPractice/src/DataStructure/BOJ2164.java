package DataStructure;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2164 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();

        int n = sc.nextInt();

        for (int i=1; i<=n; i++) {
            queue.add(i);
        }

        // 카드 한 장 남을 때 까지
        while (queue.size() > 1) {
            // 맨 위 카드 버리기
            queue.poll();
            // 맨 위 카드를 아래로 이동시키는 부분
            int tmp = queue.poll();
            queue.add(tmp);
        }

        // 마지막 카드 한 장 출력
        System.out.println(queue.poll());
    }
}
