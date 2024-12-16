package TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2623 {
    static int[] degrees;
    static ArrayList<ArrayList<Integer>> arr;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        // 가수의 수
        int N = Integer.parseInt(st.nextToken());
        // 보조 PD의 수
        int M = Integer.parseInt(st.nextToken());

        degrees = new int[N+1];
        arr = new ArrayList<>();

        for (int i=0; i<=N; i++) {
            arr.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            String[] line = br.readLine().split(" ");
            // PD가 담당한 가수의 수
            int size = Integer.parseInt(line[0]);
            for(int j=2; j<size+1; j++) {
                // 가수들의 순서
                int a = Integer.parseInt(line[j-1]);
                int b = Integer.parseInt(line[j]);

                arr.get(a).add(b);
                degrees[b]++;
            }
        }


        // 위상정렬
        queue = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            if (degrees[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append(curr+"\n");
            for (int next : arr.get(curr)) {
                degrees[next]--;
                if (degrees[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // 순서 정하는게 불가능할 경우
        boolean flag = false;
        for (int i=1; i<=N; i++) {
            if (degrees[i] != 0) {
                flag = true; break;
            }
        }

        if (flag) {
            System.out.println(0);
        } else {
            System.out.println(sb.toString());
        }
    }
}
