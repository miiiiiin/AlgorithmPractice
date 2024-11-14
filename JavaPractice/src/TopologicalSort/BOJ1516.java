package TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516
{
    static ArrayList<ArrayList<Integer>> arr;
    // 진입 차수 배열
    static int[] degrees;
    // 자기 자신 짓는데 걸리는 시간
    static int[] selfBuild;
    static int[] result;
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        degrees = new int[N+1];
        selfBuild = new int[N+1];
        result = new int[N+1];

        for (int i=0; i<=N; i++) {
            arr.add(new ArrayList<>());

        }

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            // 건물 짓는데 걸리는 시간
            selfBuild[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                arr.get(num).add(i);
                degrees[i]++;

            }
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
            for (int next : arr.get(curr)) {
                result[next] = Math.max(result[next], result[curr]+selfBuild[curr]);
                degrees[next]--;
                if (degrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i=1; i<=N; i++) {
            System.out.println(result[i] + selfBuild[i]);
        }
    }
}
