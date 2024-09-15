package Sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2751RE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        br.close();

        Collections.sort(list);

        for(int i : list){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
