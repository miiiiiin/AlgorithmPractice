package Greedy;

import java.util.Scanner;

public class BOJ1541 {

    static int result = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        // - 기준으로 나눔
        String[] splitted = str.split("-");

        for (int i=0; i<splitted.length; i++) {
            int sumValue = sum(splitted[i]);

            // -로 나뉘어진 부분
            if (i == 0) {
                result += sumValue;
            } else {
                // + 부분
                result -= sumValue;
            }
        }
        System.out.println(result);
    }

    private static int sum(String line) {
        int sum = 0;
        // + 기준으로 나눔
        String[] cal = line.split("[+]");
        for (int i=0; i<cal.length; i++) {
            sum += Integer.parseInt(cal[i]);
        }
        return sum;
    }
}
