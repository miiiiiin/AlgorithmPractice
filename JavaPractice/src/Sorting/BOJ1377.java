package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n+1];

        for (int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        // 배열 오름차순 정렬
        Arrays.sort(numbers);

        for (int i=0; i<n; i++) {

        }
    }
}

class mData implements Comparable<mData> {

    int index, value;

    public mData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    // value 기준 오름차순 정렬 함수 Comparable
    // value 기준 오름차순 정렬
    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}
