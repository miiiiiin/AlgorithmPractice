package Implementation;

import java.util.Arrays;
import java.util.Scanner;

public class STR7698 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] arr = new int[T];

        for (int i=0; i<T; i++) {
            arr[i] = sc.nextInt();
            System.out.println(convert(arr[i]));
        }
    }

    public static String convert(int vote) {
        StringBuilder sb = new StringBuilder();

        int remainder = vote % 5;
        int count = 0;
        for (int i=1; i<vote - remainder; i++) {
            if (i % 5 == 0) {
                continue;
            }
            count++;
            sb.append("+");

            if (count == 4) {
                sb.append(" ");
                count = 0;
            }
        }

        for (int i=0; i<remainder; i++) {
            sb.append("|");
        }
        return sb.toString();
    }
}
