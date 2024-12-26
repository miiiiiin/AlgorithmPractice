package Segment_Tree;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10868 {
    static long[] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 수 개수
        int N = sc.nextInt();
        // 구간 최솟값 구하는 횟수
        int M = sc.nextInt();

        int treeHeight = 0;
        int length = N;

        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        // 트리 배열 크기
        int treeSize = (int) Math.pow(2, treeHeight+1);
        // 리프노드 시작 인덱스
        int leftNodeStartIndex = treeSize / 2 -1;

        tree = new long[treeSize + 1];
        Arrays.fill(tree, Integer.MAX_VALUE);

        for (int i=leftNodeStartIndex+1; i<=leftNodeStartIndex + N; i++) {
            tree[i] = sc.nextLong();
        }

        // 초기 트리 생성
        setTree(treeSize - 1);

        for (int i=0; i<M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            start = start + leftNodeStartIndex;
            end = end + leftNodeStartIndex;
            System.out.println(getMin(start, end));
        }
    }

    private static long getMin(int start, int end) {
        long min = Long.MAX_VALUE;
        while (start <= end) {
            if (start % 2 == 1) {
                min = Math.min(min, tree[start]);
                start++;
            }
            start = start / 2;
            if (end % 2 == 0) {
                min = Math.min(min, tree[end]);
                end--;
            }
            end = end / 2;
        }
        return min;
    }

    private static void setTree(int i) {
        while (i != 1) {
            // 트리의 인덱스/2 부분(부모 노드)의 값과 현재 값을 비교하여 현재의 값이 더 작을 때
            // 해당 값을 트리의 인덱스 / 2 부분(부모 노드에 저장)
            if (tree[i/2] > tree[i]) {
                tree[i/2] = tree[i];
            }
            i--;
        }
    }
}
