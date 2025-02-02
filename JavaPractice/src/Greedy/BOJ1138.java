package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 왼쪽에 있어야 하는 키 큰 사람 수
        int[] leftCount = new int[N];
        // 최종 줄 서는 순서 (결과 배열)
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            leftCount[i] = Integer.parseInt(st.nextToken());
        }

        // 키 작은 순대로 배치 (키 큰 순서대로 하면 자리 배치 규칙 꼬임..)
        for (int i=0; i<N; i++) {
            int left = leftCount[i];
            // 건너뛴 자리 수
            int count = 0;

            // left 개수만큼 건너뛰고 삽입 (왼쪽에 있는 키 큰 사람 수만큼)
            for (int j=0; j<N; j++) {
                // 빈 자리면
                if (arr[j] == 0) {
                    // left 개수 만큼 건너 뛰었으면
                    if (count == left) {
                        // i + 1 키를 가진 사람 배
                        arr[j] = i+1;
                        break;
                    }
                    // 자리 건너뛰기
                    count++;
                }
            }
        }

        for (int i=0; i<N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
