package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        // 인출하는데 걸리는 시간 합배열
        int[] sum = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 삽입 정렬 O(n^2)
        for (int i=1; i<n; i++) {
            int insert_point = i;
            int insert_value = numbers[i];
//            System.out.println("insert_value = " + insert_value);
            // 역순으로 반복
            for (int j=i-1; j>=0; j--) {
                // 현재 범위에서 삽입 위치 찾기
//                System.out.println("----j = " + j);
//                i = 1
//                        ----j = 0
//                i = 2
//                        ----j = 1
//                        ----j = 0
//                i = 3
//                        ----j = 2
//                        ----j = 1
//                        ----j = 0
//                i = 4
//                        ----j = 3
//                        ----j = 2
//                        ----j = 1
//                        ----j = 0
//
                // numbers[i]가 더 큰 원소값을 찾아서 삽입 위치 선택
                if (numbers[i] > numbers[j]) {
//                    System.out.println("numbers i = " + numbers[i] + ", numbers j = " + numbers[j]);
                    insert_point = j + 1;
//                    System.out.println("insert point = " + insert_point);
                    break;
                }

                if (j==0) {
                    insert_point = 0;
                }
            }

//            System.out.println("------insert = " + insert_point);
            for (int j = i; j > insert_point; j--) {
//                System.out.println("j = " + j);
//                System.out.println("numbers j = " + numbers[j] + ", numbers j-1 = " + numbers[j-1]);
                numbers[j] = numbers[j-1];
            }

//            System.out.println("numbers i = " + numbers[i] + ", insertval = " + insert_value);

            numbers[insert_point] = insert_value;
//            System.out.println("point = " + insert_point + ", numbers = " + insert_value);
//            System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
        }

        // 합 배열 연산
        // 오름차순으로 정렬된 numbers 배열의 첫번째 값을 sum의 첫번째 값으로 초기화
        sum[0] = numbers[0];
        // 합 배열 초깃값 정답 값에 초기화
        int answer = sum[0];

        for (int i=1; i<n; i++) {
            // 합 배열 공식
            sum[i] = sum[i-1] + numbers[i];
            answer += sum[i];
//            System.out.println("====Arrays.toString(sum) = " + Arrays.toString(sum));
        }

        System.out.println(answer);
    }
}
