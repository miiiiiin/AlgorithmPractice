package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1931 {

    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 회의의 수
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작
            arr[i][0] = Integer.parseInt(st.nextToken());
            // 종료
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            // 종료 시간이 빠른 순서대로 정렬
            @Override
            public int compare(int[] start, int[] end) {
                // 종료 시간이 시작 시간과 같을 때
                if (start[1] == end[1]) {
                    return start[0] - end[0];
                }
                return start[1] - end[1];
            }
        });
        // 회의실 배정 횟수 저장
        int count = 0;
        // 회의 종료 시간 저장
        int end = -1;

        for (int i=0; i<N; i++) {
            // 회의 시작 시간이 회의 종료 시간과 겹치지 않는 다음 회의일 경우
            if (arr[i][0] >= end) {
                // 종료 시간 업데이트
                end = arr[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
