package SWE;

import java.util.Scanner;

public class SWE2072 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int sum = 0;
            for (int j=0; j<10; j++) {
                int num = sc.nextInt();
                if (num % 2 != 0) {
                    sum += num;
                }
            }
            System.out.println("#" + test_case + " " + sum);
        }
    }
}
