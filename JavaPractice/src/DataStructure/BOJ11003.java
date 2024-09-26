package DataStructure;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 슬라이딩 윈도우
public class BOJ11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // deque 선언
        Deque<Node> deque = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            // 새로운 값 들어올 때마다 정렬 대신 현재 수보다 큰 값을 덱에서 제거하여 시간복잡도 줄임
            int now = Integer.parseInt(st.nextToken());

            // 덱 마지막 위치에서부터 들어오는 값보다 큰 값은 덱에서 제거
            while (!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }

            // 덱의 마지막 위치에 now 저장
            deque.addLast(new Node(now, i));

            // 덱의 1번째 위치부터 l의 범위를 벗어난 값(index - l)을 덱에서 제거
            // 범위에서 벗어난 값은 덱에서 제거
            if (deque.getFirst().index <= i - l) {
                deque.removeFirst();
            }
            // 덱의 첫 번째 데이터 출력
            bw.write(deque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    static class Node {
        public int value, index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
