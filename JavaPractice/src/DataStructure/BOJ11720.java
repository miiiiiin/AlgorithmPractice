package DataStructure;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ11720 {

    static ArrayList<Integer> arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new ArrayList<>(n+1);

        for (int i=0; i<=n; i++) {
            arr.add(sc.nextInt());
        }

    }
}
