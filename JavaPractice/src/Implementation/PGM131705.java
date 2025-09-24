package Implementation;

import java.io.IOException;

public class PGM131705 {
    public static void main(String[] args) throws IOException {
        int solution = solution(new int[]{-3, -2, -1, 0, 1, 2, 3});
        System.out.println(solution);
    }

    public static int solution(int[] number) {
        int answer = 0;
        for (int i=0; i<number.length; i++) {
            for (int j=i+1; j<number.length; j++) {
                for (int k=j+1; k<number.length; k++) {
                    if ((number[i] + number[j] + number[k]) == 0) { answer++; }
                }
            }
        }
        return answer;
    }
}
