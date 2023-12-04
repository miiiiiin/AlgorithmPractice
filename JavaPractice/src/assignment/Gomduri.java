package assignment;
import java.util.ArrayList;

// 청소해야하는 구역: 2^2 길이 도로
// 구역에 재활용 쓰레기가 없으면 A 만큼의 힘을 들여 청소
// 구역에 재활용 쓰레기가 있다면 B×(구역의 길이)×(재활용 쓰레기의 개수)의 힘을 들여 청소
// 구역의 길이가 2 이상인 경우, 반으로 나누어 각각의 구역을 따로 청소

// 예를 들어 길이가 4이고 재활용 쓰레기가 다음과 같이 배치된 구역이 있다고 하자. (🥤 = 재활용 쓰레기, ☐ = 빈 구간)
//
//        🥤☐🥤☐
//
//        이 경우 다음 둘 중 한가지 방법으로 청소할 수 있다.
//
//        재활용 쓰레기가 2개 있기 때문에 전체 구역을 B×4×2의 힘을 들여 청소한다.
//        두 구역(🥤☐, 🥤☐)으로 나누어 청소한다.


public class Gomduri {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int n = 4;
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(1);
        pos.add(3);

        long minEffort = getEffort(a, b, n, pos);
        System.out.println(minEffort);
    }

//    private static int divideAndConquer(int A, int B, int n, ArrayList<Integer> pos, int left, int right) {
//
//    }

    public static long getEffort(int a, int b, int n, ArrayList<Integer> pos) {

        System.out.println("n 체크:" + n);
        long answer = 0;
        int mid = n / 2;
        System.out.println("mid 값 확인:" + mid);

        if (n == 1) {
            System.out.println("n==1 확인:" + n);
            if (pos.isEmpty()) {
                return a;
            }
        } else if (mid == 1) {
            System.out.println("mid value 1 확인:" + mid);
            return a;
        } else {

            // 재활용 쓰레기의 개수가 2개 이상이면
            // b×(구역의 길이)×(재활용 쓰레기의 개수)의 힘 vs 중앙값 기준 두 구역으로 나누어 청소(n/2)
            // 두 개의 연산값 중 힘이 더 적게 드는 것 출력

            // 반으로 나눈 구역에 재활용 쓰레기가 있는지 확인
            boolean hasTrash = false;

            for (int i = 0; i < pos.size(); i++) {
                if (pos.get(i) % 2 != 0) {
                    hasTrash = true;
                    break;
                }
            }

            // 구역을 나누어 청소할 경우, 중앙 기준 각각 절반을 청소할 때 필요한 힘 계산
//                long halfEffort = getEffort(a, b, mid, pos);

            long firstEffort = getEffort(a, b, mid, pos);
            long secondEffort = getEffort(a, b, n - mid, pos);

            return  firstEffort;
        }

        return (long) 0;
    }
}
