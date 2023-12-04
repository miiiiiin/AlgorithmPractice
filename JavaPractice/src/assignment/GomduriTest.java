package assignment;
import java.util.ArrayList;import java.util.ArrayList;

public class GomduriTest {
//    public static int getMinEffort(int A, int B, int n, ArrayList<Integer> pos) {
//        return divideAndConquer(A, B, n, pos, 0, n - 1);
//    }
public static int getMinEffort(int A, int B, int n, ArrayList<Integer> pos) {
    return divideAndConquer(A, B, n, pos, 1, n - 1); // left 아규먼트를 1로 수정
}


    private static int divideAndConquer(int A, int B, int n, ArrayList<Integer> pos, int left, int right) {
        // Base Case: 길이가 1인 경우
        if (left == right) {
            if (pos.contains(left + 1)) {
                return B;
            } else {
                return A;
            }
        }

        // 길이가 2 이상인 경우
        int minEffort = Integer.MAX_VALUE;

        for (int i = left; i < right; i++) {
            // 왼쪽과 오른쪽 구역으로 나누어 처리
            int leftEffort = divideAndConquer(A, B, n, pos, left, i);
            int rightEffort = divideAndConquer(A, B, n, pos, i + 1, right);

            // 두 구역을 청소하는 데 필요한 힘을 계산
            int totalEffort = leftEffort + rightEffort;

            // 재활용 쓰레기를 고려한 힘 계산
            int numTrash = 0;
            for (int j = left; j <= right; j++) {
                if (pos.contains(j + 1)) {
                    numTrash++;
                    System.out.println("numtrash:" + numTrash);
                }
            }
            totalEffort += (numTrash > 0) ? (B * numTrash * (right - left + 1)) : 0;

            // 최소 힘 선택
            minEffort = Math.min(minEffort, totalEffort);
        }

        return minEffort;
    }

    public static void main(String[] args) {
        int A = 1;
        int B = 2;
        int n = 4;
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(1);
        pos.add(3);

        int result = getMinEffort(A, B, n, pos);
        System.out.println("Minimum Effort: " + result);
    }
}
