package Implementation;

import java.io.*;
import java.util.*;

public class STR9657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        // 공격 l, r (Li부터 Ri까지 공격이 이루어짐) Ri - Li = 4

        st = new StringTokenizer(br.readLine());
        int firstLeft = Integer.parseInt(st.nextToken());
        int firstRight = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int secondLeft = Integer.parseInt(st.nextToken());
        int secondRight = Integer.parseInt(st.nextToken());

        int[] first = new int[firstRight];
        Arrays.fill(first, 1);

        for (int i=0; i<first.length; i++) {
            for (int j=0; j<M; j++) {
                // System.out.println("knife = " + first[i] + ", horse =" + map[i][j]);

                if (first[i] == map[i][j]) {
                    map[i][j] = 0;
                }
            }
        }

        System.out.println("map =" + Arrays.toString(map));
    }
}
