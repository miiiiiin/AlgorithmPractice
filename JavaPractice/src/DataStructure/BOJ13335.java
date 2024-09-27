package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 다리 위에는 단지 w 대의 트럭만 동시에 올라갈 수 있다.
        // 동시에 다리 위에 올라가 있는 트럭들의 무게의 합은 다리의 최대하중인 L보다 작거나 같아야 한다.

        // 다리를 건너는 트럭 수
        int n = Integer.parseInt(st.nextToken());
        // 다리의 길이
        int w = Integer.parseInt(st.nextToken());
        // 다리의 최대하중
        int l = Integer.parseInt(st.nextToken());

        // 다리 건너기 전의 트럭들
        Queue<Integer> truck = new LinkedList<>();
        // 다리 위 트럭이 얼마나 있는지 정보를 담는 큐
        Queue<Integer> bridge = new LinkedList<>();
        // 다리 지나는 시간 수 카운팅
        int count = 0;
        // 다리 무게
        int bridgeWeight = 0;

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }

        // weights의 일부 합 <= l (최대 하중)
        // 트럭이 다리를 건너는 순서는 FIFO 형태
        for (int i=0; i<w; i++) {
            // 현재 다리에 올라와있는 트럭 무게 초기화
            bridge.add(0);
        }

        // bridge 위에 아무것도 없을 때까지
        while (!bridge.isEmpty()) {
            count++;
            bridgeWeight -= bridge.poll();

            if (!truck.isEmpty()) {
                // 트럭 큐의 맨 앞의 값과 현재 다리 위에 올라있는 트럭의 무게 합이 l보다 낮을 때,
                // 다리에 트럭 한 대씩 추가
                if ((truck.peek() + bridgeWeight) <= l) {
                    bridgeWeight += truck.peek();
                    bridge.add(truck.poll());
                } else {
                    // 다리의 최대 하중을 초과하기 때문에 트럭이 다리에 못올라갈 경우
                    bridge.add(0);
                }
            }
        }
        System.out.println(count);
    }
}
