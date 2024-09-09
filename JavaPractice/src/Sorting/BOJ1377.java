package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        mData[] numbers = new mData[n];

        for (int i=0; i<n; i++) {
            // value 입력받아서 index 값과 함께 저장
            numbers[i] = new mData(Integer.parseInt(br.readLine()), i);
//            System.out.println("numbers[i].value = " + numbers[i].value);
        }

        // 배열 오름차순 정렬 : O(nLog n)
        Arrays.sort(numbers);
        // 정렬 전 index값에서 정렬 후의 index 값을 빼서 나온 최댓값 구하기
        int maxValue = 0;
        for (int i=0; i<n; i++) {
            // numbers[i].index : 정렬 전 index
            // i : 정렬 후 index
            // maxValue 갱신
            if (maxValue < numbers[i].index - i) {
                maxValue = numbers[i].index - i;
            }
        }
        // swap이 일어나지 않는 반복문이 한 번 더 실행되는 것을 감안하여
        // 최댓값에 1을 더한 것을 답으로 출력
        System.out.println(maxValue + 1);
    }
}

// 데이터의 index와 value값을 다루는 클래스
class mData implements Comparable<mData> {
    int index, value;

    public mData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    // value 기준 오름차순 정렬 함수 Comparable
    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}
