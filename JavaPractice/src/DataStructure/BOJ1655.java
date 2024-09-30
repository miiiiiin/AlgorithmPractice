package DataStructure;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 시간 복잡도 O(NlogN)
 * 힙 삽입/삭제가 O(logN)인데
 * N번 동안 삽입/삭제를 하므로
 */
public class BOJ1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        // 데이터 개수가 홀수면 중간에 있는 값
        // 짝수라면 가운데 두 값 중 작은 값
        // 최대 힙 : 중간값 이하의 값 (top()이 중간값이 됨) => 현재까지 입력된 값들 중 가장 큰 중간값 항상 유지
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        // 최소 힙 : 나머지 절반 (중간값보다 큰 값 저장)
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i=0; i<n; i++) {
            int curr = Integer.parseInt(br.readLine());

            // 두 힙 중 사이즈가 더 작은 힙에 값을 삽입함 (같으면 최대힙)
            if (left.size() <= right.size()) {
                // 최대 힙
                left.add(curr);
            } else {
                // 최소 힙
                right.add(curr);
            }

            // 최대 힙의 top이 최소 힙의 top보다 크면 두 힙의 top값을 스왑하여 중간값 조건 맞춤
            if (!right.isEmpty() && !left.isEmpty() && (left.peek() > right.peek())) {
                int temp = left.poll();
                left.offer(right.poll());
                right.offer(temp);
            }

            // 매번 최대힙(left)의 top값이 중간값이므로 출력
            sb.append(left.peek() + "\n");
        }

        System.out.println(sb.toString());
    }
}
