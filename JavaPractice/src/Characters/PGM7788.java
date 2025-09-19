package Characters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PGM7788 {
    public static void main(String[] args) throws IOException {
        int solution = solution(13, 17);
        System.out.println(solution);
    }

    public static int solution(int left, int right) {
        int answer = 0;
        for (int i=left; i<=right; i++) {
            int count = 0; // 약수 개수
            for (int j=1; j<=i; j++) {
                if (i%j==0) {
                    count++;
                }
            }
            System.out.println(count);

            if (count % 2 == 0) {
                answer += i;
            } else {
                answer -= i;
            }
        }
        return answer;
    }

}
