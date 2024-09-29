package DataStructure;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 슬라임 에너지 => 2 이상의 자연수로 표현됨.
 * 슬라임 합성 과정 : 2마리 합성해서 1마리 만들어냄 (A*B)인 슬라임 만들 수 있음
 * N마리의 슬라임을 합해서 1마리의 슬라임으로 만들고자 함.
 * 그러나 A*B만큼의 전기 에너지가 필요함 => 이 값이 최소가 되도록 합성하는 것이 목표
 *  각 테스트 케이스마다 슬라임을 끝까지 합성했을 때 청구될 비용의 최솟값을 1, 000, 000, 007로 나눈 나머지를 출력한다
 *  전기 에너지가 전혀 필요하지 않은 경우엔 1 을 출력
 **/
public class BOJ14698 {
    /**
     * 최소 전기 에너지 구하는 법
     * 작은 값끼리 먼저 합성하면, 전체 곱셈 결과 최소화할 수 있음.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 초기 전기 에너지 (아무 합성도 하지 않은 상태)
        long energy = 0;
        long mod = 1000000007;

        // 테스트케이스 수
        int t = Integer.parseInt(br.readLine());
        for (int i=0; i<t; i++) {
            energy = 1;

            // 슬라임의 수
            int n = Integer.parseInt(br.readLine());

            // 각 슬라임의 에너지 저장하는 큐
            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                long mul = a * b;
                energy *= mul % mod;
                energy %= mod;
                // 모듈러 연산 하지 않은 값을 삽입
                pq.add(mul);
            }
            bw.write(energy + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
