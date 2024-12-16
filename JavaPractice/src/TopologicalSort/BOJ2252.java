package TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2252 {
    // 진입차수 배열
    static int[] degrees;
    static  ArrayList<ArrayList<Integer>> arr;
    static Queue<Integer> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 학생 수
        int N = sc.nextInt();
        // 키 비교 횟수
        int M = sc.nextInt();

        degrees = new int[N+1];
        arr = new ArrayList<>();

        for (int i=0; i<=N; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            // 학생 A가 학생 B의 앞에 서야 한다 (노드와 에지)
            int S = sc.nextInt();
            int E = sc.nextInt();


            // 노드에 에지 연결
            arr.get(S).add(E);
            // 진입 차수 배열 저장
            degrees[E]++;
        }

        // 위상 정렬
        queue = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            // 진입차수가 0이면 큐에 추가
            if (degrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            ArrayList<Integer> currarr = arr.get(curr);
            System.out.print(curr + " ");
            for (int next : arr.get(curr)) {
                degrees[next]--;
                if (degrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}
