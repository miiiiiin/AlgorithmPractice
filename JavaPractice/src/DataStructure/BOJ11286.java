package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 연산의 개수 N(1≤N≤100,000)이 주어짐
// 최대 범위가 100,0000이므로 시간 복잡도 : O(NlogN)

// x가 0이 아니면 배열에 x값 추가하는 연산 (큐에 추가, 우선 순위 큐 정렬 기준 자동 정렬)
// x가 0이면 배열에서 절대값 가장 작은 값 출력(음수 우선 출력)하고, 배열에서 제거
// 배열이 빈 상태일 때에는 가장 작은 값 출력 시도 시 "0"을 출력
public class BOJ11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 우선순위 큐 선언
        PriorityQueue<Integer> queue = new PriorityQueue<>((n1, n2) -> {
            // 정렬 로직 담당
            // 절대값 작은 데이터 우선
            int first_abs = Math.abs(n1);
            int second_abs = Math.abs(n2);

            // 절대값 같을 경우 음수 우선
            if (first_abs == second_abs)
                return n1 > n2 ? 1 : -1;

            // n1이 더 크면 양수 리턴, n2이 더 크면 음수 리턴
            return  first_abs - second_abs;
            // 리턴값 음수/양수냐로 비교 정렬 기준 세움
        });

        for (int i=0; i<N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (queue.isEmpty())
                    System.out.println("0");
                else
                    System.out.println(queue.poll());
            } else {
                queue.add(x);
            }
        }
    }
}
