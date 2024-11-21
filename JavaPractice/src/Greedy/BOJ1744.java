package Greedy;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1744 {
    public static void main(String[] args) {
        // 최대힙, 최소힙
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        //최대힙 (양수) 내림차순 정렬
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());
        //최소힙 (음수)
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();

        // 0의 개수 카운트
        int zeroCount = 0;
        // 1의 개수 카운트
        int oneCount = 0;

        for (int i=0; i<N; i++) {
            int data = sc.nextInt();
            if (data > 1) {
                plusPq.add(data);
            } else if (data < 1) {
                minusPq.add(data);
            } else if (data == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
        }

        int sum = 0;
        // pluspq에 값이 1 초과하여 있을 때, (2개를 묶어서 연산할 수 있으므로)
        while (plusPq.size() > 1) {
            int first = plusPq.remove();
            int second = plusPq.remove();
            sum = sum + (first * second);
        }

        if (!plusPq.isEmpty()) {
            sum = sum + plusPq.remove();
        }

        while (minusPq.size() > 1) {
            int first = minusPq.remove();
            int second = minusPq.remove();
            sum = sum + (first * second);
        }

        if (!minusPq.isEmpty()) {
            sum = sum + minusPq.remove();
        }

        // 2개씩 묶어서(곱) 더했을 때, 최대합 출력
        // oneCount 합해서 최종 값 구함
        sum += oneCount;
        System.out.println(sum);

    }
}
