package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class STR7626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 마을의 위치 리스트
        int[] arr = new int[N];

        int minDiff = Integer.MAX_VALUE;
        List<Integer> diffs = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 두 마을 거리 간 최소 거리 찾기
        for (int i=0; i<N-1; i++) {
            int diff = arr[i+1] - arr[i];
            if (minDiff > diff) {
                minDiff = diff;
            }
            diffs.add(diff);
        }

        // 최소 차이 나는 경우의 수 세기
        int count = 0;

        for (int diff : diffs) {
            if (diff == minDiff) {
                count++;
            }
        }

        System.out.println(count);
    }
}
