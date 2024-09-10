package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] numArr = new int[str.length()];

        for (int i=0; i<str.length(); i++) {
            numArr[i] = Integer.parseInt(str.substring(i, i+1));
        }

        for (int i=0; i<str.length(); i++) {
            int maxIdx = i;
            for (int j=i+1; j<str.length(); j++) {
                // 최대값 찾아 내림차순 정렬
//                System.out.println("numarr = " + numArr[j] + ", numarr maxValue = " + numArr[maxIdx]);
                if (numArr[j] > numArr[maxIdx]) {
                    maxIdx = j;
                }
            }
            if (numArr[i] < numArr[maxIdx]) {
                int temp = numArr[i];
                numArr[i] = numArr[maxIdx];
                numArr[maxIdx] = temp;
            }
        }

        for (int i=0; i<str.length(); i++) {
            System.out.print(numArr[i]);
        }
    }
}
