package Segment_Tree;

import java.util.Scanner;

public class BOJ2042 {

    // 세그먼트 트리 배열
    static long[] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 수 개수
        int N = sc.nextInt();
        // 수의 변경이 일어나는 횟수
        int M = sc.nextInt();
        // 구간의 합을 구하는 횟수
        int K = sc.nextInt();

        int treeHeight = 0;
        int length = N;

        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        // 트리 배열 크기 : 2^k * 2
        int treeSize = (int) Math.pow(2, treeHeight+1);
        // 2^k - 1 부터 1번 쪽으로 채움 (리프 노드 시작 인덱스)
        int leftNodeStartIndex = treeSize / 2 - 1;
        tree = new long[treeSize + 1];

        // 데이터 리프노드에 입력 (리프 노드 시작 위치 : 2^k)
        for (int i=leftNodeStartIndex+1; i<=leftNodeStartIndex+N; i++) {
            tree[i] = sc.nextLong();
        }

        // 초기 트리 생성
        setTree(treeSize-1);

        for (int i=0; i<M+K; i++) {
            long a = sc.nextLong();
            // 시작/끝 인덱스
            int start = sc.nextInt();
            long end = sc.nextLong();

            if (a == 1) {
                // 인덱스 값 바꾸기
                changeVal(leftNodeStartIndex + start, end);
            } else if (a == 2) {
                // start부터 end까지의 구간합 구함
                start = start + leftNodeStartIndex;
                end = end + leftNodeStartIndex;
                System.out.println(getSum(start, (int) end));
            } else {
                return;
            }
        }
    }

    private static long getSum(int start, int end) {
        // 부분합
        long partSum = 0;
        // 시작 인덱스와 종료 인덱스가 교차될 때까지
        while (start <= end) {
            // 해당 노드의 값 구간합에 추가하거나 시작 인덱스 증가
            if (start % 2 == 1) {
                partSum = partSum + tree[start];
                start++;
            }
            // 해당 노드의 값 구간합에 추가하거나 종료 인덱스 감소
            if (end % 2 == 0) {
                partSum = partSum + tree[end];
                end--;
            }

            start = start / 2;
            end = end / 2;
        }
        return partSum;
    }

    // index의 값을 변경하는 함수
    private static void changeVal(int index, long value) {
        long diff = value - tree[index];
        while (index>0) {
            tree[index] = tree[index] + diff;
            index = index/2;
        }
    }

    // 트리 노드 채움 (2^k - 1부터 1번쪽으로)
    private static void setTree(int i) {
        while (i != 1) {
            tree[i/2] += tree[i];
            i--;
        }
    }
}
