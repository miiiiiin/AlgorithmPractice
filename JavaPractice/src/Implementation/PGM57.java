package Implementation;

public class PGM57 {
    static int[] numbers;
    public static void main(String[] args) {
        numbers = new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String answer = solution(numbers, "left");
        System.out.println(answer);
    }

    public static String solution(int[] numbers, String hand) {
        // 초기 손가락 위치
        int left = 10;
        int right = 12;
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<numbers.length; i++) {
            // 현재 위치
            int n = numbers[i];

            if (n == 1 || n == 4 || n == 7) {
                left = n;
                sb.append("L");
            }

            if (n == 3 || n == 6 || n == 9) {
                right = n;
                sb.append("R");
            }

            if (n == 2 || n == 5 || n == 8 || n == 0) {
                if (n == 0) n = 11;

                // 현재 위치 - 누르는 위치의 절대값 (위아래로 움직이는 거리 /3, 좌우 움직이는 거리 %3)
                // 왼손과 오른손 거리 계산
                int leftDiff = (Math.abs(n - left)/3) + (Math.abs(n - left)%3);
                int rightDiff = (Math.abs(n - right)/3) + (Math.abs(n - right)%3);

                if (leftDiff == rightDiff) {
                    if (hand.contains("r")) {
                        right = n;
                        sb.append("R");
                    } else {
                        left = n;
                        sb.append("L");
                    }
                } else if (leftDiff < rightDiff) {
                    left = n;
                    sb.append("L");
                } else {
                    right = n;
                    sb.append("R");
                }
             }
        }

        return sb.toString();
    }
}
