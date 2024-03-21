package DataStructure;

import java.util.Scanner;

public class BOJ11720 {
    public static void main(String[] args) {
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String number = sc.next();
        char[] numArr = number.toCharArray();

        for (int i=0; i<numArr.length; i++) {
            // 아스키코드를 이용해서 cNum의 문자형의 값을 int로 바꿀 때는 문자열 '0'의 값을 빼줌(-48)
            sum += numArr[i] - '0';
//            sum += Integer.parseInt(String.valueOf(numArr[i]));
        }

        System.out.println(sum);
    }
}
