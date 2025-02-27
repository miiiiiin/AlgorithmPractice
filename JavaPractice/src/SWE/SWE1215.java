package SWE;

import java.util.Scanner;

public class SWE1215 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 회문 길이
            int len = sc.nextInt();

            char[][] map = new char[8][8];

            for (int i=0; i<map.length; i++) {
                String str = sc.next();
                for (int j = 0; j<map[i].length; j++) {
                    char s = str.charAt(j);
                    map[i][j] = s;
                }
            }

            //배열 탐색 후 flag의 true 여부에 따라서 cnt 증가 시켜줄 것
            boolean flag;
            int cnt = 0;

            // 행 기준 탐색
            for (int i=0; i<map.length; i++) {
                for (int j=0; j<map.length - len+1; j++) {
                    flag = true;
                    for (int k=0; k<len/2; k++) {
                        if (map[i][j+k] != map[i][j-k+len-1]) {
                            flag = false;
                        }
                        if (flag) cnt++;
                    }
                }
            }

            // 열 기준 탐색
            for(int i=0; i<map.length-len+1; i++) {
                for(int j=0; j<map.length; j++) {
                    flag = true;
                    for(int k=0; k<len/2; k++)
                        if(map[i+k][j]!=map[i-k+len-1][j])
                            flag = false;

                    if(flag) cnt ++;

                }
            }
            System.out.println("#"+test_case+" "+cnt);

        }
    }
}
