package Greedy;

import java.util.Scanner;

// 잃어버린 괄호
public class BOJ1541 {

    static int result;

    private static int sum(String line) {
        int sum = 0;

        String[] cal = line.split("[+]");
        for (int i=0; i<cal.length; i++) {
            sum += Integer.parseInt(cal[i]);
        }
        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        String[] cal = line.split("-");

        for (int i = 0; i < cal.length; i++) {
            int sum_result = sum(cal[i]);

            // 가장 앞의 데이터일 때
            if (i == 0) {
                result += sum_result;
            } else {
                result -= sum_result;
            }
        }
        System.out.println(result);
    }
}
